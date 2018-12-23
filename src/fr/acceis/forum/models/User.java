package fr.acceis.forum.models;

public class User {
	
	private String login;
	private int id;
	private String password;
	private int posts_number;
	private String salt;
	
	public User(String login, int id, String password) {
		this.login = login;
		this.id = id;
		this.password = password;
	}
	
	public int getPosts_number() {
		return posts_number;
	}

	public void setPosts_number(int posts_number) {
		this.posts_number = posts_number;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
}
