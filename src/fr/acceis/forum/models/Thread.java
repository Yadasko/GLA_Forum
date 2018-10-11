package fr.acceis.forum.models;

import java.sql.SQLException;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;

public class Thread {
	
	private int id;
	private String name;
	private int author;
	
	public Thread(int id, String name, int author) {
		this.id = id;
		this.name = name;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public User getAuthor() {
		Datafetcher d = DBUtils.getDataFetcher();
		try {
			return d.fetchUser(this.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new User(null, -1, null);
		}
	}

	
	@Override
	public String toString() {
		return "Thread [id=" + id + ", name=" + name + ", author=" + author + "]";
	}
	
	

}
