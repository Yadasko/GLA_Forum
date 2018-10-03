package fr.acceis.forum.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Datafetcher_hsqldb implements Datafetcher {
	
	private Connection connection = null;
	
	public Datafetcher_hsqldb() throws SQLException {
		connection = DriverManager.getConnection("D:\\M2\\GLA\\TP1\\Servers\\forum\\data", "sa",  "");
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
		
		if (result.next()) {
			System.out.println(result.toString());
			return new User(result.getString("login"), result.getInt("id"), result.getString("password"));
		}
		else {
			// Return fake user. We know that id = -1 is when no user has been found
			return new User("NONE", -1, "NONE");
		}
			
	}

}
