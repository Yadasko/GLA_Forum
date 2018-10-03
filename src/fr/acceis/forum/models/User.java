package fr.acceis.forum.models;

public class User {
	
	private String login;
	private int id;
	private String password;
	
	public User(String login, int id, String password) {
		this.login = login;
		this.id = id;
		this.password = password;
	}
	
	public String toString() {
		return "[" + this.id + "] " + this.login + ":" + this.password; 
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
