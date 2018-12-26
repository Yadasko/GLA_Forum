package fr.acceis.forum.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.User;
import fr.acceis.forum.models.UserSession;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("info_msg", "Vous êtes déjà connecté.");
			if (req.getParameter("ref") != null)
				req.getRequestDispatcher(req.getParameter("ref")).forward(req, resp);
			else
				req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String passw = req.getParameter("password");

		System.out.println("Username " + username + " - Password " + passw);

		UserSession us = new UserSession(username, passw);
		us.login();

		HttpSession session = req.getSession();	
		if (us.isLogged_in()) {
			session.setAttribute("user", us);	
			resp.sendRedirect("/forum/home");
		}
		else {
			req.setAttribute("info_msg", "Utilisateur/Mot de passe incorrect.");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}

	}

//	NOT USED ANYMORE AS AUTH IS DONE IN USERSESSION MODEL (I don't remember why I did that though...)
//	protected boolean auth(String login, String password) throws SQLException {
//		//return ("admin".equals(login) && "admin".equals(password));
//
//		Datafetcher df = DBUtils.getDataFetcher();
//
//		User user = df.fetchUser(login);
//
//		if (user.getId() == -1) return false; // No one has been found	
//		return (user.getPassword().equals(password) && user.getLogin().equals(login));
//	}

}