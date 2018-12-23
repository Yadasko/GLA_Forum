package fr.acceis.forum.models;

import java.io.Serializable;
import java.sql.SQLException;

import fr.acceis.forum.metier.Cryptutils;
import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;

public class UserSession implements Serializable {

    private String  login;
    private int     user_id;
    private String  password;

    private boolean logged_in;
    
    public UserSession(String login, String password) {
    	this.login = login;
    	this.password = password;
    }
    
    public void login() {
    	Datafetcher df = DBUtils.getDataFetcher();
		
		User user;
		try {
			user = df.fetchUser(this.login);
			this.user_id = user.getId();
			
			if (user.getId() == -1) this.logged_in = false; // No one has been found	
			
			Cryptutils.verifyPassword(password, user.getPassword(), user.getSalt());
			
			this.logged_in = Cryptutils.verifyPassword(password, user.getPassword(), user.getSalt());
		} catch (SQLException e) {
			e.printStackTrace();
			this.logged_in = false;
		}
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLogged_in() {
		return logged_in;
	}

	public void setLogged_in(boolean logged_in) {
		this.logged_in = logged_in;
	}
    
	public String toString() {
		return "UserSession[" + this.user_id + ": " + this.login + " ? " + this.isLogged_in() + "]";
	}
    
    
}