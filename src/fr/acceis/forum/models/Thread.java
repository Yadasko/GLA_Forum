package fr.acceis.forum.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;
import fr.acceis.forum.models.Message;

public class Thread {
	
	private int id;
	private String name;
	private int author;
	private int views;
	private int numResponses;
	
	public Thread(int id, String name, int author, int views) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.views = views;
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
	
	public int getViews() {
		return this.views;
	}
	
	public List<Message> getAssociatedMessages() {
		try {
			return DBUtils.getDataFetcher().fetchAssociatedMessages(this.id);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Message>();
		}
	}
	
	public int getNumResponses() {
		return numResponses;
	}

	public void setNumResponses(int numResponses) {
		this.numResponses = numResponses;
	}	
	
	
	@Override
	public String toString() {
		return "Thread [id=" + id + ", name=" + name + ", author=" + author + "]";
	}


}
