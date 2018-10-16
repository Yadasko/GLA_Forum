package fr.acceis.forum.models;

import java.io.Serializable;
import java.sql.SQLException;

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
    	
    	this.logged_in = this.login();
    }
    
    private boolean login() {
    	Datafetcher df = DBUtils.getDataFetcher();
		
		User user;
		try {
			user = df.fetchUser(this.login);
			this.user_id = user.getId();
			
			if (user.getId() == -1) return false; // No one has been found	
			return (user.getPassword().equals(password) && user.getLogin().equals(login));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
    
    
    
}