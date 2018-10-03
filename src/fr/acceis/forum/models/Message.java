package fr.acceis.forum.models;

public class Message {
	
	private int id;
	private String author;
	private String content;
	private int thread_id;
	
	public Message(int id, String author, String content, int thread_id) {
		this.id = id;
		this.author = author;
		this.content = content;
		this.thread_id = thread_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}
	
}
