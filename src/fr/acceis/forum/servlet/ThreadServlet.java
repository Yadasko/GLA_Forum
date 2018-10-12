package fr.acceis.forum.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.Message;

public class ThreadServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int thread_id = Integer.parseInt(req.getParameter("id"));
		
		List<Message> messages;
		Datafetcher df = DBUtils.getDataFetcher();
		try {
			 messages = df.fetchAssociatedMessages(thread_id);
		} catch (SQLException e) {
			e.printStackTrace();
			messages = new ArrayList<Message>();
		}
		
		req.setAttribute("messages", messages);
		req.getRequestDispatcher("/WEB-INF/jsp/thread.jsp").forward(req, resp);
		
		// We update view count only now so it does not affect load time (why not?)
		
		try {
			df.updateThreadViewCount(thread_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
