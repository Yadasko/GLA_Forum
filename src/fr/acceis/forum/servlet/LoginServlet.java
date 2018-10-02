package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("username") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("info_msg", "Vous êtes déjà connecté.");
			req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
		}
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String passw = req.getParameter("password");
		
		System.out.println("Username " + username + " - Password " + passw);
		
		HttpSession session = req.getSession();	
		
		if (auth(username, passw)) {
			session.setAttribute("username", username);
			req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("info_msg", "Utilisateur/Mot de passe incorrect.");
			//session.setAttribute("info_msg", "Utilisateur/Mot de passe incorrect.");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}
		
	}
	
	protected boolean auth(String login, String password) {
		return ("admin".equals(login) && "admin".equals(password));
	}

}