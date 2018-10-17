package fr.acceis.forum.metier;

import java.sql.SQLException;
import java.util.List;

import fr.acceis.forum.models.Message;
import fr.acceis.forum.models.User;
import fr.acceis.forum.models.Thread;

public interface Datafetcher {

	public boolean auth(String username, String password);
	
	public boolean addUser(String username, String password); 
	
	public String getAllData();
	
	public List<Message> fetchAssociatedMessages(int thread_id) throws SQLException;
	
	public List<Thread> fetchThreads() throws SQLException;
	
	public User fetchUser(String login) throws SQLException;
	
	public User fetchUser(int id) throws SQLException;
	
	public void updateThreadViewCount(int thread_id) throws SQLException;
	
	public void updateThreadViewCount(int thread_id, int view_count) throws SQLException;
	
	public void addThreadAnswer(int thread_id, String content, int user_id) throws SQLException;
	
	public int createNewThread(String name, String content, int author_id) throws SQLException; // Create a new thread and post the first message to it
	
	public void closeConnection();
	
}
