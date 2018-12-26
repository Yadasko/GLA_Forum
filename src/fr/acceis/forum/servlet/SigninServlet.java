package fr.acceis.forum.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.acceis.forum.metier.Cryptutils;
import fr.acceis.forum.metier.CustomLogger;
import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.PasswordModel;
import fr.acceis.forum.models.User;

public class SigninServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {
			req.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("info_msg", "Vous êtes déjà connecté. Veuillez vous déconnecter.");
			if (req.getParameter("ref") != null)
				req.getRequestDispatcher(req.getParameter("ref")).forward(req, resp);
			else
				req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String passw = req.getParameter("password1");
		String passw2 = req.getParameter("password2");
		
		if (!passw.equals(passw2)) {
			req.setAttribute("info_msg", "Vos deux mots de passes ne correspondent pas.");
			req.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
			return;
		}
		
		if (passw.length() < 6) {
			req.setAttribute("info_msg", "Votre mot de passe doit avoir 6 ou + de caractères.");
			req.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
			return;
		}
		
		Datafetcher df = DBUtils.getDataFetcher();
		try {
			User u = df.fetchUser(username);
			
			if (u.getId() != -1) {
				req.setAttribute("info_msg", "Un utilisateur avec ce nom existe déjà !");
				req.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
				return;
			}
			
			PasswordModel pm = Cryptutils.hashPassword(passw);
			df.createUser(username, pm.hash, pm.salt);
			
			resp.sendRedirect("login");
			
			CustomLogger.logInfo("A new user has been created by IP " + req.getRemoteAddr());
		} catch (SQLException e) {
			req.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(req, resp);
			CustomLogger.logError("SQL ERROR: " + e.getMessage());
		}
	}


}