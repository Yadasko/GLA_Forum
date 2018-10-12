package fr.acceis.forum.models;

public class Message {
	
	private int id;
	private int author_id;
	private String author_name;
	private String content;
	private int thread_id;

	public Message(int id, int author_id, String content, int thread_id) {
		this.id = id;
		this.author_id = author_id;
		this.content = content;
		this.thread_id = thread_id;
	}
	
	public Message(int id, int author_id, String content, int thread_id, String author_name) {
		this(id, author_id, content, thread_id);
		this.author_name = author_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAuthorId() {
		return author_id;
	}

	public void setAuthorId(int author_id) {
		this.author_id = author_id;
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

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	
}
