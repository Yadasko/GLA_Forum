package fr.acceis.forum.metier;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.acceis.forum.models.UserSession;


public class CustomLogger {

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {

		logger.info("Entering application.");
		logger.error("OH THIS IS BAD!");
	}
	
	public static void logError(String message) {
		logger.error(message);
	}
	
	public static void logError(UserSession us, String message) {
		logger.error("[( " + us.getUser_id() + ") " + us.getLogin() + "] - " + message);
	}
	
	public static void logInfo(String message) {
		logger.info(message);
	}
	
	public static void logException(Exception e) {
		logger.error(e.getMessage());
	}
}