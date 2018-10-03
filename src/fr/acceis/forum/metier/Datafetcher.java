package fr.acceis.forum.metier;

public interface Datafetcher {

	public boolean auth(String username, String password);
	
	public boolean addUser(String username, String password); 
	
	
}
