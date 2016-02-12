package com.gl.mychat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.gl.mychat.Utils.Utility;
import com.gl.mychat.database.TblFriend;
import com.gl.mychat.database.TblFriendDAO;
import com.gl.mychat.database.TblFriendId;
import com.gl.mychat.database.TblFriendapplication;
import com.gl.mychat.database.TblFriendapplicationDAO;
import com.gl.mychat.database.TblFriendapplicationId;
import com.gl.mychat.database.TblUser;
import com.gl.mychat.database.TblUserDAO;

public class FriendServlet extends HttpServlet {
	private Key key = ProjectInit.key;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			if (action.equals("apply")) {// 申请好友
				String applicantUsername = Utility.validate(token, key);
				String targetUsername = req.getParameter("targetusername");
				String description = req.getParameter("description");
				if (applicantUsername != null) {
					TblFriendapplicationDAO friendapplicationDAO = new TblFriendapplicationDAO();
					TblUserDAO userDAO = new TblUserDAO();
					TblUser applicantUser = (TblUser) userDAO.findByUsername(
							applicantUsername).get(0);
					TblUser targetUser = (TblUser) userDAO.findByUsername(
							targetUsername).get(0);
					TblFriendapplicationId friendapplicationId = new TblFriendapplicationId(
							applicantUser, targetUser, new Date());
					TblFriendapplication friendapplication = new TblFriendapplication(
							friendapplicationId, description, "UNREAD");
					Session session = friendapplicationDAO.getSession();
					Transaction transaction = session.beginTransaction();
					friendapplicationDAO.save(friendapplication);
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("queryapply")) {// 查询申请
				String myUsername = Utility.validate(token, key);
				if (myUsername != null) {

					TblFriendapplicationDAO friendapplicationDAO = new TblFriendapplicationDAO();
					List<TblFriendapplication> friendapplications = friendapplicationDAO
							.findAll();
					// 查看申请信息，目标是自己，且未读
					for (TblFriendapplication friendapplication : friendapplications) {
						if (friendapplication.getId().getTblUser_1()
								.getUsername().equals(myUsername)
								&& friendapplication.getStatus().equals(
										"UNREAD")) {
							TblUser applicantUser = friendapplication.getId()
									.getTblUser();
							JSONObject friendAppObject = new JSONObject();
							friendAppObject.put("applicant",
									applicantUser.getUsername());
							friendAppObject.put("description",
									friendapplication.getDescription());
							jsonArray.add(friendAppObject);
						}
					}
					if (jsonArray.size() > 0)
						jsonObject.put("application", jsonArray);
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("queryfriend")) {// 查询全部好友
				String myUsername = Utility.validate(token, key);
				if (myUsername != null) {
					TblFriendDAO friendDAO = new TblFriendDAO();
					List<TblFriend> friends = friendDAO.findAll();
					for (TblFriend friend : friends) {
						JSONObject friendObject = new JSONObject();
						if (friend.getId().getTblUser().getUsername()
								.equals(myUsername)) {
							TblUser friendUser = friend.getId().getTblUser_1();
							friendObject.put("username",
									friendUser.getUsername());
							friendObject.put("friendnote",
									friend.getDescription());
							jsonArray.add(friendObject);
						}
					}
					if (jsonArray.size() > 0)
						jsonObject.put("friends", jsonArray);
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("accept") || action.equals("reject")) {// 同意申请或者拒绝申请
				String myUsername = Utility.validate(token, key);
				String applicantusername = req.getParameter("applicant");
				if (myUsername != null) {
					// 由于ApplicationId由两个用户和时间决定，时间不好确定，就都找出来，再用两个用户过滤
					TblFriendapplicationDAO friendapplicationDAO = new TblFriendapplicationDAO();
					List<TblFriendapplication> friendapplications = friendapplicationDAO
							.findAll();
					Session session = friendapplicationDAO.getSession();
					Transaction transaction = session.beginTransaction();
					for (TblFriendapplication friendapplication : friendapplications) {
						TblUser applicantUser = friendapplication.getId()
								.getTblUser();
						TblUser targetUser = friendapplication.getId()
								.getTblUser_1();
						// 申请人符合，被申请人是自己，那么我就同意申请
						if (applicantUser.getUsername().equals(
								applicantusername)
								&& targetUser.getUsername().equals(myUsername)
								&& friendapplication.getStatus().equals(
										"UNREAD")) {
							friendapplication.setStatus(action.toUpperCase());// 同意或者拒绝申请，标志位由action决定
							session.update(friendapplication);
						}
					}
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("delete")) {// 双向删除好友
				String myUsername = Utility.validate(token, key);
				String targetUsername = req.getParameter("targetusername");
				if (myUsername != null) {
					TblUserDAO userDAO = new TblUserDAO();
					TblUser user1 = (TblUser) userDAO
							.findByUsername(myUsername).get(0);
					TblUser user2 = (TblUser) userDAO.findByUsername(
							targetUsername).get(0);
					TblFriendDAO friendDAO = new TblFriendDAO();
					TblFriend friend1 = friendDAO.findById(new TblFriendId(
							user1, user2));
					TblFriend friend2 = friendDAO.findById(new TblFriendId(
							user2, user1));
					Session session = friendDAO.getSession();
					Transaction transaction = session.beginTransaction();
					friendDAO.delete(friend1);
					friendDAO.delete(friend2);
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("updatefriendnote")) {// 修改好友备注
				String myUsername = Utility.validate(token, key);
				String targetUsername = req.getParameter("targetusername");
				String friendNote = req.getParameter("friendnote");
				if (myUsername != null) {
					TblUserDAO userDAO = new TblUserDAO();
					TblUser myUser = (TblUser) userDAO.findByUsername(
							myUsername).get(0);
					TblUser targetUser = (TblUser) userDAO.findByUsername(
							targetUsername).get(0);

					TblFriendDAO friendDAO = new TblFriendDAO();
					TblFriendId friendId = new TblFriendId(myUser, targetUser);
					TblFriend friend = friendDAO.findById(friendId);
					if (friend == null)
						throw new Exception("该好友不存在");
					Session session = friendDAO.getSession();
					Transaction transaction = session.beginTransaction();
					friend.setDescription(friendNote);
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
				} else {
					throw new Exception("invalid token");
				}
			} else if (action.equals("queryblacklist")) {// 查询黑名单
				/*
				 * String myUsername = Utility.validate(token, key); if
				 * (myUsername != null) { TblUserDAO userDAO = new TblUserDAO();
				 * for (TblBlacklist blackItem : blacklist) { JSONObject
				 * targetObject = new JSONObject(); TblUser friendUser =
				 * blackItem.getId().getTblUser_1(); if
				 * (friendUser.getTblPicture() != null)
				 * targetObject.put("portrait", Base64
				 * .encodeBase64String(friendUser
				 * .getTblPicture().getPicture()));
				 * targetObject.put("friendusername", friendUser.getUsername());
				 * targetObject.put("nickname", friendUser.getNickname()); if
				 * (blackItem.getDescription() != null)
				 * targetObject.put("description", blackItem.getDescription());
				 * jsonArray.add(targetObject); } if (jsonArray.size() > 0)
				 * jsonObject.put("blacklist", jsonArray);
				 * jsonObject.put("status", 1); } else { throw new
				 * Exception("invalid token"); }
				 */
			} else if (action.equals("block")) {// 拉黑好友
				/*
				 * String myUsername = Utility.validate(token, key); String
				 * targetUsername = req.getParameter("targetusername"); String
				 * description = req.getParameter("description"); if (myUsername
				 * != null) { TblUserDAO userDAO = new TblUserDAO(); TblUser me
				 * = (TblUser) userDAO.findByUsername(myUsername) .get(0);
				 * TblUser target = (TblUser) userDAO.findByUsername(
				 * targetUsername).get(0); TblBlacklistId blacklistId = new
				 * TblBlacklistId(me, target); TblBlacklist blacklist = new
				 * TblBlacklist(blacklistId, description); TblBlacklistDAO
				 * blacklistDAO = new TblBlacklistDAO(); Session session =
				 * blacklistDAO.getSession(); Transaction transaction =
				 * session.beginTransaction(); blacklistDAO.save(blacklist);
				 * transaction.commit(); session.close();
				 * jsonObject.put("status", 1); } else { throw new
				 * Exception("invalid token"); }
				 */
			} else if (action.equals("unblock")) {// 解除黑名单
				/*
				 * String myUsername = Utility.validate(token, key); String
				 * targetUsername = req.getParameter("targetusername"); if
				 * (myUsername != null) { TblUserDAO userDAO = new TblUserDAO();
				 * TblUser me = (TblUser) userDAO.findByUsername(myUsername)
				 * .get(0); TblUser target = (TblUser) userDAO.findByUsername(
				 * targetUsername).get(0); TblBlacklistId blacklistId = new
				 * TblBlacklistId(me, target); TblBlacklistDAO blacklistDAO =
				 * new TblBlacklistDAO(); TblBlacklist blacklist =
				 * blacklistDAO.findById(blacklistId); Session session =
				 * blacklistDAO.getSession(); Transaction transaction =
				 * session.beginTransaction(); blacklistDAO.delete(blacklist);
				 * transaction.commit(); session.close();
				 * jsonObject.put("status", 1); } else { throw new
				 * Exception("invalid token"); }
				 */
			} else if (action.equals("isavailable")) {// 检查是否为好友或已拉黑
				String myUsername = Utility.validate(token, key);
				String targetUsername = req.getParameter("targetusername");
				TblUserDAO userDAO = new TblUserDAO();
				TblFriendDAO friendDAO = new TblFriendDAO();
				TblUser myUser = (TblUser) userDAO.findByUsername(myUsername)
						.get(0);
				TblUser targetUser = (TblUser) userDAO.findByUsername(
						targetUsername).get(0);
				TblFriendId friendId = new TblFriendId(myUser, targetUser);
				TblFriend friend = friendDAO.findById(friendId);
				if (friend != null) {
					System.out.println("friend非空");
					jsonObject.put("available", !friend.getBlock());
				} else {
					jsonObject.put("available", false);
					System.out.println("friend空");
				}
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
			printWriter.flush();
			printWriter.close();
		}
	}
}
