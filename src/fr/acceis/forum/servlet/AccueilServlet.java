package fr.acceis.forum.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.Thread;

public class AccueilServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Datafetcher df = DBUtils.getDataFetcher();
		List<Thread> threads = null;
		try {
			threads = df.fetchThreads();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*threads.forEach(thread -> { // OH GOD IS THIS JAVASCRIPT ?!
			thread = (Thread) thread;
			
			
		});*/
		req.setAttribute("threads", threads);
		
		req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
