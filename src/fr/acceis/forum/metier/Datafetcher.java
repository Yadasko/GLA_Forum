package fr.acceis.forum.metier;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import fr.acceis.forum.models.Message;
import fr.acceis.forum.models.User;
import fr.acceis.forum.models.Thread;

public interface Datafetcher {
	
	public List<Message> fetchAssociatedMessages(int thread_id) throws SQLException;
	
	public List<Thread> fetchThreads() throws SQLException;
	
	public User fetchUser(String login) throws SQLException;
	
	public User fetchUser(int id) throws SQLException;
	
	public void createUser(String username, String password) throws SQLException;
	
	public void updateThreadViewCount(int thread_id) throws SQLException;
	
	public void updateThreadViewCount(int thread_id, int view_count) throws SQLException;
	
	public void addThreadAnswer(int thread_id, String content, int user_id) throws SQLException;
	
	public int createNewThread(String name, String content, int author_id) throws SQLException; // Create a new thread and post the first message to it
	
	public void updateUserAvatar(int user_id, InputStream blob, int blob_size) throws SQLException;
	
	public byte[] fetchUserAvatar(int user_id) throws SQLException;
	
	public byte[] fetchDefaultAvatar() throws SQLException;
	
	public void closeConnection();
	
}
