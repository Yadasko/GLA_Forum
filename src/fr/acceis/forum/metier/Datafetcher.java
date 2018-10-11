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
	
	public List<Message> fetchAssociatedMessages(int thread_id);
	
	public List<Thread> fetchThreads() throws SQLException;
	
	public User fetchUser(String login) throws SQLException;
	
	public User fetchUser(int id) throws SQLException;
	
	public void closeConnection();
	
}
