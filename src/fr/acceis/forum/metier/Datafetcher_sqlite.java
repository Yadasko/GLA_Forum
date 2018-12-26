package fr.acceis.forum.metier;

import java.util.ArrayList;
import java.util.List;

import fr.acceis.forum.models.Message;
import fr.acceis.forum.models.Thread;
import fr.acceis.forum.models.User;

import java.io.InputStream;
import java.sql.*;

public class Datafetcher_sqlite implements Datafetcher {

	private Connection connection = null;

	public Datafetcher_sqlite() {
		try {
			Class.forName("org.sqlite.JDBC");
			//connection = DriverManager.getConnection("jdbc:sqlite:F:/Documents/M2/GLA/GLA_Forum/forum.db");
			connection = DriverManager.getConnection("jdbc:sqlite:D:/M2/GLA/TP1/Servers/forum/forum.db");

			CustomLogger.logInfo("Opened database successfully");
		} catch (ClassNotFoundException e) {
			CustomLogger.logException(e);
		} catch (SQLException e) {
			CustomLogger.logException(e);
		}
	}

	@Override
	public List<Message> fetchAssociatedMessages(int thread_id) throws SQLException {

		String SQL =  "SELECT MESSAGES.content, Users.login, Users.id, Threads.name, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count" + 
					" FROM Messages, Threads  INNER JOIN USERS ON MESSAGES.author_id = USERS.id AND MESSAGES.thread_id = Threads.ID WHERE MESSAGES.thread_id = ? ORDER BY MESSAGES.id";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, thread_id);
		
		ResultSet result = ps.executeQuery();

		List<Message> messages = new ArrayList<Message>();

		while(result.next()) {
			messages.add(new Message(0, result.getInt("id"), result.getString("content"), 0, result.getString("login"), result.getInt("msg_count"), result.getString("name")));
		}
		ps.close();
		return messages;
	}

	@Override
	public List<Thread> fetchThreads() throws SQLException {
		/* Pas d'input utilisateur, pas besoin de passer par des prepared statements */
		ArrayList<Thread> list = new ArrayList<Thread>();
		Statement stmt = this.connection.createStatement();

		String SQL =  "SELECT THREADS.id, THREADS.name, THREADS.views, (SELECT COUNT(*) FROM MESSAGES WHERE thread_id = THREADS.id) AS responses_count, USERS.login " +
				"FROM THREADS INNER JOIN USERS WHERE THREADS.author_id = USERS.id ORDER BY THREADS.id";

		CustomLogger.logInfo("Executing SQL: " + SQL);

		ResultSet result = stmt.executeQuery( SQL );

		while(result.next()) {
			Thread t = new Thread(result.getInt("id"), result.getString("name"), 0, result.getInt("views"), result.getString("login"), result.getInt("responses_count"));
			list.add(t);
		}

		stmt.close();
		return list;
	}

	@Override
	public void createUser(String username, String password, String salt) throws SQLException {
		String SQL = "INSERT INTO `Users`(`login`,`password`, `salt`) VALUES (?, ?, ?)";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, salt);
		
		ps.execute();
		ps.close();
	}
	
	@Override
	public User fetchUser(String login) throws SQLException {
		String SQL =  "SELECT Users.id, Users.login, Users.password, Users.salt, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count FROM Users WHERE login = ? ";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setString(1, login);
		ResultSet result = ps.executeQuery();

		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			u.setPosts_number(result.getInt("msg_count"));
			u.setSalt(result.getString("salt"));
			ps.close(); // Sqlite differs from hsqldb as we can't close the statement once the query is done but once we have fetched everything
			return u;
		}
		else {
			ps.close();
			// Return fake user. We know that id = -1 means no user has been found
			return new User("NONE", -1, "NONE");
		}
	}

	public User fetchUser(int id) throws SQLException {
		
		String SQL =  "SELECT Users.id, Users.login, Users.password, Users.salt, (SELECT COUNT(*) FROM MESSAGES WHERE author_id = Users.id) AS msg_count FROM Users WHERE id = ? ";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, id);

		ResultSet result = ps.executeQuery();

		if (result.next()) {
			User u = new User(result.getString("login"), result.getInt("id"), result.getString("password"));
			u.setPosts_number(result.getInt("msg_count"));
			u.setSalt(result.getString("salt"));
			ps.close(); // Sqlite differs from hsqldb as we can't close the statement once the query is done but once we have fetched everything
			return u;
		}
		else {
			ps.close();
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
				CustomLogger.logException(e);
			}

	}

	
	@Override
	public void updateThreadViewCount(int thread_id) throws SQLException {
		String SQL =  "UPDATE THREADS SET  views = views + 1 WHERE id = ?";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, thread_id);
		
		ps.execute();
		ps.close();
	}

	@Override
	public void updateThreadViewCount(int thread_id, int view_count) throws SQLException {
		String SQL =  "UPDATE THREADS SET views = ? WHERE id = ? ";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, view_count);
		ps.setInt(2, thread_id);
		
		CustomLogger.logInfo("Executing SQL: " + SQL);

		ps.execute();
		ps.close();
	}

	@Override
	public void addThreadAnswer(int thread_id, String content, int user_id) throws SQLException {
		String SQL = "INSERT INTO `Messages`(`author_id`,`content`,`thread_id`) VALUES (?, ?, ?);";
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, user_id);
		ps.setString(2, content);
		ps.setInt(3, thread_id);
		
		CustomLogger.logInfo("Executing SQL: " + SQL);
		
		ps.execute();
		ps.close();
		
	}

	@Override
	public int createNewThread(String name, String content, int author_id) throws SQLException {		
		String SQL = "INSERT INTO `Threads` (`name`,`author_id`, `views`) VALUES (?, ?, 0)";
	
		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setString(1, name);
		ps.setInt(2, author_id);
		
		ps.execute();
		
		// Grab the thread id that has been created
		Statement st = this.connection.createStatement();
		SQL = "SELECT last_insert_rowid() AS id";
		
		ResultSet res = st.executeQuery(SQL);
		
		int id = -1;
		if (res.next()) id = res.getInt("id");
		else return id;
		
		// Add the first answer to the thread
		this.addThreadAnswer(id, content, author_id);
		
		return id;
	}

	@Override
	public void updateUserAvatar(int user_id, InputStream blob, int blob_size) throws SQLException {
		/* Way simpler than I thought! */
		/* This will try to insert a row with user_id. If error (caused by unique constraint), it will replace it with a new row (new row id) */
		String SQL = "INSERT OR REPLACE INTO `Avatars` (user_id, image) values (?, ?)";
		
		CustomLogger.logInfo("updatingUserAvatar with user_id: " + user_id);
		CustomLogger.logInfo("Using SQL: " + SQL);

		PreparedStatement ps = this.connection.prepareStatement(SQL);
		
		ps.setInt(1, user_id);
		ps.setBinaryStream(2, blob, blob_size);
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public byte[] fetchUserAvatar(int user_id) throws SQLException {		
		String SQL = "SELECT image FROM Avatars WHERE user_id = ?";
		
		PreparedStatement stmt = this.connection.prepareStatement(SQL);
		stmt.setInt(1, user_id);
		
		ResultSet result  = stmt.executeQuery();
		
		if (result.next()) {
			//return result.getBinaryStream("image");
			return result.getBytes("image");
		}
		else {
			stmt.close();
		}
		return null;
	}

	// Fetching default avatar from DB so we don't have any issues with different path files
	public byte[] fetchDefaultAvatar() throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		String SQL = "SELECT image FROM Avatars WHERE user_id = -1";
		
		ResultSet result  = stmt.executeQuery( SQL );
		
		if (result.next()) {
			return result.getBytes("image");
		}
		else {
			stmt.close();
		}
		return null;
	}

}
