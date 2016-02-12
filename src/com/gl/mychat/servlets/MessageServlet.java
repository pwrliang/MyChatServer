package com.gl.mychat.servlets;

import io.jsonwebtoken.impl.Base64Codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.mychat.Utils.Utility;
import com.gl.mychat.database.TblMessageQueue;
import com.gl.mychat.database.TblMessageQueueDAO;
import com.gl.mychat.database.TblPicture;
import com.gl.mychat.database.TblPictureDAO;
import com.gl.mychat.database.TblUser;
import com.gl.mychat.database.TblUserDAO;

public class MessageServlet extends HttpServlet {
	private final Key key = ProjectInit.key;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Session session;
		Transaction transaction;
		PrintWriter printWriter = resp.getWriter();
		InputStream inputStream = req.getInputStream();
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		JSONObject jsonObject = new JSONObject();
		try {
			if (action.equals("sendmessage")) {// 发送消息
				String senderUsername = Utility.validate(token, key);
				String receiverUsername = req.getParameter("receiver");
				String contentType = req.getParameter("contenttype");
				if (senderUsername != null) {
					TblUserDAO userDAO = new TblUserDAO();
					TblUser sender = (TblUser) userDAO.findByUsername(
							senderUsername).get(0);
					TblUser receiver = (TblUser) userDAO.findByUsername(
							receiverUsername).get(0);
					TblMessageQueueDAO messageQueueDAO = new TblMessageQueueDAO();
					TblMessageQueue message = null;
					if (contentType.equals("text")) {
						// String textContent = req.getParameter("content");
						String textContent = new String(
								Utility.getBytesFromStream(inputStream),
								"UTF-8");
						message = new TblMessageQueue(receiver, sender,
								textContent, contentType.toUpperCase(),
								"UNREAD", new Date());
					} else if (contentType.equals("picture")) {
						TblPictureDAO pictureDAO = new TblPictureDAO();
						// 保存图片到数据库
						session = pictureDAO.getSession();
						transaction = session.beginTransaction();
						byte[] portraitBytes = Utility
								.getBytesFromStream(inputStream);
						TblPicture picture = new TblPicture(portraitBytes,
								new Date(), null);
						pictureDAO.save(picture);
						transaction.commit();
						session.close();
						// 保存信息
						message = new TblMessageQueue(receiver, sender,
								String.valueOf(picture.getPicId()),
								contentType.toUpperCase(), "UNREAD", new Date());
					}
					session = messageQueueDAO.getSession();
					transaction = session.beginTransaction();
					messageQueueDAO.save(message);
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
			printWriter.close();
			inputStream.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			if (action.equals("receivemessage")) {// 接收消息
				String receiverUsername = Utility.validate(token, key);
				if (receiverUsername != null) {
					TblUserDAO userDAO = new TblUserDAO();
					TblUser receiver = (TblUser) userDAO.findByUsername(
							receiverUsername).get(0);
					TblMessageQueueDAO messageQueueDAO = new TblMessageQueueDAO();
					SQLQuery query = messageQueueDAO
							.getSession()
							.createSQLQuery(
									"SELECT * FROM TBL_MESSAGE_QUEUE WHERE TO_ID = ? AND STATUS='UNREAD' ORDER BY POST_DATE ASC");
					query.addEntity(TblMessageQueue.class);
					query.setLong(0, receiver.getUserId());
					List<TblMessageQueue> messages = query.list();

					for (TblMessageQueue message : messages) {
						// 变更状态为已读
						JSONObject messageObject = new JSONObject();
						messageObject.put("sender", message
								.getTblUserByFromId().getUsername());
						if (message.getContentType().equals("PICTURE")) {
							TblPictureDAO pictureDAO = new TblPictureDAO();
							TblPicture picture = pictureDAO.findById(Long
									.valueOf(message.getContent()));
							messageObject.put("content", Base64
									.encodeBase64String(picture.getPicture()));
						} else if (message.getContentType().equals("TEXT")) {
							messageObject.put("content", message.getContent());
						}
						messageObject.put("contenttype",
								message.getContentType());
						messageObject.put("postdate", message.getPostDate()
								.toString());
						jsonArray.add(messageObject);
						Session session = messageQueueDAO.getSession();
						Transaction transaction = session.beginTransaction();
						message.setStatus("READ");
						session.update(message);
						transaction.commit();
						session.close();
					}
					if (jsonArray.size() > 0)
						jsonObject.put("messages", jsonArray);
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
		}
	}

}
