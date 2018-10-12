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
	private int author_id;
	private String author_name;
	private int views;
	private int responses_count = 0;
	
	public Thread(int id, String name, int author, int views) {
		this.id = id;
		this.name = name;
		this.author_id = author;
		this.views = views;
	}
	
	public Thread(int id, String name, int author, int views, String author_name, int responses_count) {
		this(id, name, author, views);
		this.author_name = author_name;
		this.responses_count = responses_count;
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
	
	
	public int getResponses_count() {
		return responses_count;
	}

	public void setResponses_count(int responses_count) {
		this.responses_count = responses_count;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	@Override
	public String toString() {
		return "Thread [id=" + id + ", name=" + name + ", author=" + author_id + ", responses_count=" + responses_count + "]";
	}


}
