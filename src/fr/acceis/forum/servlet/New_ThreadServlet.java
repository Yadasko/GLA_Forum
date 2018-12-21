package fr.acceis.forum.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.models.UserSession;

public class New_ThreadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check here if connected
		req.getRequestDispatcher("/WEB-INF/jsp/new_thread.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String content = req.getParameter("content");
		String name = req.getParameter("thread_name");
		UserSession us = (UserSession) req.getSession().getAttribute("user");
		
		if (us == null || !us.isLogged_in()) resp.sendRedirect("/forum/home");
		
		System.out.println("content: " + content + " - name: " + name);
		
		try {
			System.out.println(us.getUser_id());
			int id = DBUtils.getDataFetcher().createNewThread(name, content, us.getUser_id());
			resp.sendRedirect("/forum/thread?id=" + id);

		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("/forum/home");
		}
	}
}
