package fr.acceis.forum.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.User;
import fr.acceis.forum.models.UserSession;

public class FileUploadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null)
		{
			
			UserSession us = (UserSession) session.getAttribute("user");
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(new ServletRequestContext(req));
				
				for (FileItem item : items) {
					System.out.println("Item: " + item.getName() + " coming from: " + item.getFieldName() + " with content: " + item.getContentType());
					if (item.getFieldName().equals("avatar")) {
						String extension = item.getContentType().split("/")[1];
						File f = new File("D:\\M2\\GLA\\TP1\\Servers\\forum\\WebContent\\fichiers\\avatars\\" + us.getUser_id() + "." + extension);
						item.write(f);
					}
					
				}
					
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}


}