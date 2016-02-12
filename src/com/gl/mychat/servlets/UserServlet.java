package com.gl.mychat.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gl.mychat.Utils.ErrorAnalysisUtility;
import com.gl.mychat.Utils.Utility;
import com.gl.mychat.database.TblFriend;
import com.gl.mychat.database.TblFriendDAO;
import com.gl.mychat.database.TblNotification;
import com.gl.mychat.database.TblNotificationDAO;
import com.gl.mychat.database.TblPicture;
import com.gl.mychat.database.TblPictureDAO;
import com.gl.mychat.database.TblUser;
import com.gl.mychat.database.TblUserDAO;

/**
 * 模块功能 注册 登录 修改个人信息
 * */
public class UserServlet extends HttpServlet {
	private final Key key = ProjectInit.key;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Session session = null;
		Transaction transaction = null;
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		System.out.println("method:doGet");
		String token = req.getParameter("token");
		System.out.println("action:" + action);
		JSONObject jsonObject = new JSONObject();
		try {
			if (action.equals("validatetoken")) {// 验证token
				if (Utility.validate(token, key) == null)
					throw new Exception("invalid token");
				jsonObject.put("status", 1);
			} else if (action.equals("regist")) {// 注册
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				String nickname = req.getParameter("nickname");
				String phoneNumber = req.getParameter("phonenumber");
				TblUser tblUser = new TblUser(username, password, nickname,
						phoneNumber);
				tblUser.setGender("MALE");
				tblUser.setRegistDate(new Date());
				TblUserDAO tblUserDAO = new TblUserDAO();
				session = tblUserDAO.getSession();
				transaction = session.beginTransaction();
				tblUserDAO.save(tblUser);
				transaction.commit();
				session.close();
				jsonObject.put("status", 1);
			} else if (action.equals("login")) {// 登录
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				TblUserDAO tblUserDAO = new TblUserDAO();
				TblUser tblUser = (TblUser) tblUserDAO.findByUsername(username)
						.get(0);
				if (tblUser.getPassword().equals(password)) {
					// 登录时，通过用户名产生token，进行操作是根据token取得用户名
					jsonObject.put("token",
							Utility.generateToken(username, key));
					jsonObject.put("status", 1);
				} else {
					throw new Exception("密码错误");
				}
			} else if (action.equals("queryprofilebyusername")) {// 查询他人信息
				TblUserDAO userDAO = new TblUserDAO();
				String myUsername = Utility.validate(token, key);
				if (myUsername != null) {
					String targetUsername = req.getParameter("targetusername");
					List<TblUser> users = (List<TblUser>) userDAO
							.findByUsername(targetUsername);
					if (users.size() == 0)
						throw new Exception("未搜索到该用户");
					TblUser targetUser = users.get(0);
					// 有头像就查询
					if (targetUser.getTblPicture() != null) {
						byte[] portrait = targetUser.getTblPicture()
								.getPicture();
						jsonObject.put("portrait",
								Base64.encodeBase64String(portrait));
					}
					jsonObject.put("username", targetUsername);
					jsonObject.put("nickname", targetUser.getNickname());
					jsonObject.put("gender", targetUser.getGender());
					jsonObject.put("region", targetUser.getRegion());
					jsonObject.put("description", targetUser.getDescription());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(targetUser.getRegistDate());
					String month = (calendar.get(Calendar.MONTH) + 1) + "";
					if (month.length() == 1)// 1,2,3->01,02,03
						month = "0" + month;
					String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
					if (day.length() == 1)
						day = "0" + day;
					jsonObject.put("registdate", calendar.get(Calendar.YEAR)
							+ "-" + month + "-" + day);
					jsonObject.put("phonenumber", targetUser.getPhoneNumber());
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("querynotification")) {// 查询通知
				String myUsername = Utility.validate(token, key);
				TblNotificationDAO notificationDAO = new TblNotificationDAO();
				List<TblNotification> notifications = notificationDAO.findAll();
				JSONArray jsonArray = new JSONArray();
				session = notificationDAO.getSession();
				transaction = session.beginTransaction();
				for (TblNotification notification : notifications) {
					if (notification.getTblUser().getUsername()
							.equals(myUsername)
							&& notification.getStatus().equals("UNREAD")) {
						JSONObject notifObject = new JSONObject();
						notifObject.put("notification",
								notification.getNotification());
						notifObject.put("notificationid",
								notification.getNotificationId());
						jsonArray.add(notifObject);
						notification.setStatus("READ");
					}
				}
				transaction.commit();
				session.close();
				if (jsonArray.size() > 0)
					jsonObject.put("notifications", jsonArray);
				jsonObject.put("status", 1);
			}
		} catch (Exception e) {
			try {
				jsonObject.put("status", 0);
				jsonObject.put("error", e.toString());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			printWriter.write(jsonObject.toString());
			printWriter.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Session session;
		Transaction transaction;
		PrintWriter printWriter = resp.getWriter();
		InputStream inputStream = req.getInputStream();
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		System.out.println("method:doPost");
		System.out.println("action:" + action);
		JSONObject jsonObject = new JSONObject();
		try {
			if (action.equals("updateprofile")) {// 修改个人信息
				String myUsername = Utility.validate(token, key);
				String password = req.getParameter("password");
				String nickname = req.getParameter("nickname");
				String gender = req.getParameter("gender");
				String region = req.getParameter("region");
				String description = req.getParameter("description");
				String phoneNumber = req.getParameter("phonenumber");
				if (myUsername != null) {
					TblUserDAO userDAO = new TblUserDAO();
					TblUser user = (TblUser) userDAO.findByUsername(myUsername).get(
							0);
					TblPicture picture = null;
					// 上传头像
					byte[] portraitBytes = Utility
							.getBytesFromStream(inputStream);
					if (portraitBytes != null) {
						TblPictureDAO pictureDAO = new TblPictureDAO();
						session = pictureDAO.getSession();
						transaction = session.beginTransaction();
						picture = new TblPicture(portraitBytes, new Date(),
								null);
						pictureDAO.save(picture);
						transaction.commit();
						session.close();
					}
					// 修改个人信息，有头像也把头像对象给它
					session = userDAO.getSession();
					transaction = session.beginTransaction();
					if (password != null) {
						user.setPassword(password);
					}
					if (nickname != null) {
						user.setNickname(nickname);
					}
					if (gender != null) {
						user.setGender(gender);
					}
					if (region != null) {
						user.setRegion(region);
					}
					if (description != null) {
						user.setDescription(description);
					}
					if (phoneNumber != null) {
						user.setPhoneNumber(phoneNumber);
					}
					if (picture != null) {
						user.setTblPicture(picture);
					}
					session.update(user);
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			}
		} catch (Exception e) {
			try {
				jsonObject.put("status", 0);
				jsonObject.put("error", e.toString());
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			printWriter.write(jsonObject.toString());
			printWriter.flush();
			printWriter.close();
			inputStream.close();
		}
	}

}
