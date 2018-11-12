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
import fr.acceis.forum.models.User;

public class UserProfileServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getParameter("id") != null) {
			try {
				User u = DBUtils.getDataFetcher().fetchUser(Integer.parseInt(req.getParameter("id")));
				System.out.println(u.getLogin());
				if (u.getId() != -1) { // User has been found
					req.setAttribute("id", u.getId());
					req.setAttribute("login",  u.getLogin());
					req.setAttribute("posts", u.getPosts_number());
					
					req.getRequestDispatcher("/WEB-INF/jsp/userprofile.jsp").forward(req, resp);
				}
				else resp.sendRedirect("/forum/home");

			} catch (SQLException e) {
				e.printStackTrace();
				resp.sendRedirect("/forum/home");
			}
		}
		else resp.sendRedirect("/forum/home");
		// These if else are crappy!
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
