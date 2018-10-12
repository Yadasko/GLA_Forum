package fr.acceis.forum.metier;

import java.util.ArrayList;
import java.util.List;

import fr.acceis.forum.models.Message;
import fr.acceis.forum.models.Thread;
import fr.acceis.forum.models.User;
import java.sql.*;

public class Datafetcher_sqlite implements Datafetcher {

	private Connection connection = null;

	public Datafetcher_sqlite() {
		try {
			Class.forName("org.sqlite.JDBC");
			//connection = DriverManager.getConnection("jdbc:sqlite:F:/Documents/M2/GLA/GLA_Forum/forum.db");
			connection = DriverManager.getConnection("jdbc:sqlite:D:/M2/GLA/TP1/Servers/forum/forum.db");

			System.out.println("Opened database successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	public List<Message> fetchAssociatedMessages(int thread_id) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT * FROM MESSAGES WHERE thread_id = " + thread_id + " ORDER BY id";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );

		List<Message> messages = new ArrayList<Message>();

		while(result.next()) {
			messages.add(new Message(result.getInt("id"), result.getString("author_id"), result.getString("content"), result.getInt("thread_id")));
		}
		stmt.close();
		return messages;
	}

	@Override
	public List<Thread> fetchThreads() throws SQLException {
		ArrayList<Thread> list = new ArrayList<Thread>();
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT * FROM Threads";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );

		while(result.next()) {
			Thread t = new Thread(result.getInt("id"), result.getString("name"), result.getInt("author_id"), result.getInt("views"));
			list.add(t);
		}

		stmt.close();
		return list;
	}

	@Override
	public User fetchUser(String login) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT * FROM USERS WHERE login = '" + login + "'";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );


		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			stmt.close(); // Sqlite differs from hsqldb as we can't close the statement one the query is done but once we have fetched everything
			return u;
		}
		else {
			stmt.close();
			// Return fake user. We know that id = -1 means no user has been found
			return new User("NONE", -1, "NONE");
		}
	}

	public User fetchUser(int id) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT * FROM USERS WHERE id = '" + id + "'";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );


		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			stmt.close(); // Sqlite differs from hsqldb as we can't close the statement one the query is done but once we have fetched everything
			return u;
		}
		else {
			stmt.close();
			// Return fake user. We know that id = -1 means no user has been found
			return new User("NONE", -1, "NONE");
		}
	}

	@Override
	public void closeConnection() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

}
