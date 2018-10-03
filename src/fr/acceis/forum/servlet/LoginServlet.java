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
import fr.acceis.forum.metier.User;

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
		try {
			if (auth(username, passw)) {
				session.setAttribute("username", username);	
				req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
			}
			else {
				req.setAttribute("info_msg", "Utilisateur/Mot de passe incorrect.");
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected boolean auth(String login, String password) throws SQLException {
		//return ("admin".equals(login) && "admin".equals(password));
		
		Datafetcher df = DBUtils.getDataFetcher();
		
		User user = df.fetchUser(login);
		
		if (user.getId() == -1) return false; // No one has been found	
		return (user.getPassword().equals(password) && user.getLogin().equals(login));
	}

}