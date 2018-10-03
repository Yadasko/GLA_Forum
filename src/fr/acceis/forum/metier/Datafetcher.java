package fr.acceis.forum.metier;

import java.sql.SQLException;

import fr.acceis.forum.models.User;

public interface Datafetcher {

	public boolean auth(String username, String password);
	
	public boolean addUser(String username, String password); 
	
	public String getAllData();
	
	public User fetchUser(String login) throws SQLException;
	
	public void closeConnection();
	
}
