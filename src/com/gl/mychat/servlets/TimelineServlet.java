package com.gl.mychat.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gl.mychat.Utils.Utility;
import com.gl.mychat.database.TblTimeline;
import com.gl.mychat.database.TblTimelineDAO;
import com.gl.mychat.database.TblUser;
import com.gl.mychat.database.TblUserDAO;

public class TimelineServlet extends HttpServlet {
	private static final Key key = ProjectInit.key;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		String action = req.getParameter("action");
		String token = req.getParameter("token");
		JSONObject jsonObject = new JSONObject();
		if (action.equals("post")) {
			try {
				String username = Utility.validate(token, key);
				String content = req.getParameter("content");
				String visibility = req.getParameter("visibility");
				if (username != null) {
					TblTimelineDAO timelineDAO = new TblTimelineDAO();
					TblUserDAO userDAO = new TblUserDAO();
					TblUser user = (TblUser) userDAO.findByUsername(username)
							.get(0);
					TblTimeline timeline = new TblTimeline(user, content,
							visibility, null);
					Session session = timelineDAO.getSession();
					Transaction transaction = session.beginTransaction();
					timelineDAO.save(timeline);
					transaction.commit();
					session.close();
					jsonObject.put("status", 1);
					jsonObject.put("error", "");
				} else {
					throw new Exception("invalid token");
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

}
