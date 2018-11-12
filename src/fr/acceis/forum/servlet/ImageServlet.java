package fr.acceis.forum.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import java.nio.file.Files;

public class ImageServlet extends HttpServlet {

	   public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException{
	      Integer userId = Integer.parseInt(request.getParameter("userId"));
	      
	      Datafetcher df = DBUtils.getDataFetcher();
	      
	     try {
			byte[] bs = df.fetchUserAvatar(userId);
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			if (bs == null) {
				// Display default avatar
				byte[] bytes = df.fetchDefaultAvatar();
				bos.write(bytes);
				bos.flush();
				bos.close();
			}
			else {
				bos.write(bs);
				bos.flush();
				bos.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	   }
}
