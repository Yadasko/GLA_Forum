package fr.acceis.forum.metier;

import java.sql.SQLException;

public class Datafetcher_mysql implements Datafetcher {

	@Override
	public boolean auth(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fetchUser(String login) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
