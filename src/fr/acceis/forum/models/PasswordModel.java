package fr.acceis.forum.models;

// Stub to contain hash and salt

public class PasswordModel {

	public String hash;
	public String salt;

	public PasswordModel(String hash, String salt) {
		super();
		this.hash = hash;
		this.salt = salt;
	}
	
	public String toString() {
		return "[" + salt + "] " + hash;
	}
}
