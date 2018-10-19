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

			String SQL =  "SELECT MESSAGES.content, Users.login, Threads.name, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count" + 
					" FROM Messages, Threads  INNER JOIN USERS ON MESSAGES.author_id = USERS.id AND MESSAGES.thread_id = Threads.ID WHERE MESSAGES.thread_id = " + thread_id + " ORDER BY MESSAGES.id";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );

		List<Message> messages = new ArrayList<Message>();

		while(result.next()) {
			messages.add(new Message(0, 0, result.getString("content"), 0, result.getString("login"), result.getInt("msg_count"), result.getString("name")));
		}
		stmt.close();
		return messages;
	}

	@Override
	public List<Thread> fetchThreads() throws SQLException {
		ArrayList<Thread> list = new ArrayList<Thread>();
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT THREADS.id, THREADS.name, THREADS.views, (SELECT COUNT(*) FROM MESSAGES WHERE thread_id = THREADS.id) AS responses_count, USERS.login " +
				"FROM THREADS INNER JOIN USERS WHERE THREADS.author_id = USERS.id ORDER BY THREADS.id";

		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );

		while(result.next()) {
			Thread t = new Thread(result.getInt("id"), result.getString("name"), 0, result.getInt("views"), result.getString("login"), result.getInt("responses_count"));
			list.add(t);
		}

		stmt.close();
		return list;
	}

	@Override
	public User fetchUser(String login) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT Users.id, Users.login, Users.password, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count FROM Users WHERE login = '" + login + "'";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );


		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			u.setPosts_number(result.getInt("msg_count"));
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
		
		String SQL =  "SELECT Users.id, Users.login, Users.password, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count FROM Users WHERE id = '" + id + "'";
		System.out.println("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );


		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			u.setPosts_number(result.getInt("msg_count"));
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

	
	@Override
	public void updateThreadViewCount(int thread_id) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "UPDATE THREADS SET  views = views + 1 WHERE id = " + thread_id;
		System.out.println("Executing SQL: " + SQL);

		stmt.execute( SQL );
		stmt.close();
	}

	@Override
	public void updateThreadViewCount(int thread_id, int view_count) throws SQLException {
		Statement stmt = this.connection.createStatement();

		String SQL =  "UPDATE THREADS SET  views = " + view_count + " WHERE id = " + thread_id;
		System.out.println("Executing SQL: " + SQL);

		stmt.execute( SQL );
		stmt.close();
	}

	@Override
	public void addThreadAnswer(int thread_id, String content, int user_id) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String SQL = "INSERT INTO `Messages`(`author_id`,`content`,`thread_id`) VALUES (" + user_id + ",\"" + content + "\", " + thread_id + ");";
		System.out.println("Executing SQL: " + SQL);
		
		stmt.execute( SQL );
		stmt.close();
		
	}

	@Override
	public int createNewThread(String name, String content, int author_id) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String SQL = "INSERT INTO `Threads` (`name`,`author_id`, `views`) VALUES ('" + name + "', " + author_id + ", 0)";
		
		stmt.execute( SQL );
		
		// Grab the thread id that has been created
		SQL = "SELECT last_insert_rowid() AS id";
		ResultSet res = stmt.executeQuery(SQL);
		
		int id = -1;
		if (res.next()) id = res.getInt("id");
		else return id;
		
		// Add the first answer to the thread
		this.addThreadAnswer(id, content, author_id);
		
		return id;
	}


}
