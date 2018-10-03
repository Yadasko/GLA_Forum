package fr.acceis.forum.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.forum.models.Message;
import fr.acceis.forum.models.User;
import fr.acceis.forum.models.Thread;


public class Datafetcher_hsqldb implements Datafetcher {
	
	private Connection connection = null;
	
	public Datafetcher_hsqldb() throws SQLException {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:hsqldb:D:/M2/GLA/TP1/Servers/forum/data/", "sa",  "");
		System.out.println("CONNECTION IS SET.");
	}

	@Override
	public boolean auth(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUser(String login) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String SQL =  "SELECT * FROM Utilisateurs WHERE login = '" + login + "'";
		System.out.println("Executing SQL: " + SQL);
		
		ResultSet result = stmt.executeQuery( SQL );
		stmt.close();
		
		if (result.next()) {
			return new User(result.getString("login"), result.getInt("id"), result.getString("password"));
		}
		else {
			// Return fake user. We know that id = -1 means no user has been found
			return new User("NONE", -1, "NONE");
		}
			
	}
	

	@Override
	public List<Message> fetchAssociatedMessages(int thread_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Thread> fetchThreads() throws SQLException {
		ArrayList<Thread> list = new ArrayList<Thread>();
		Statement stmt = this.connection.createStatement();
			
		String SQL =  "SELECT * FROM Threads";
		System.out.println("Executing SQL: " + SQL);
		
		ResultSet result = stmt.executeQuery( SQL );
		stmt.close();
		
		while(result.next()) {
			list.add(new Thread(result.getInt("id"), result.getString("name")));
		}
		
		return list;
	}
	
	@Override
	public void closeConnection() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
