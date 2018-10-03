package fr.acceis.forum.metier;

import java.sql.SQLException;

public class DBUtils {
	
	private static Datafetcher db = null;
	
	public static Datafetcher getDataFetcher() {
		if (DBUtils.db == null) {
			try {
				DBUtils.db = new Datafetcher_hsqldb();
				System.out.println("CREATED OUR DATAFETCHER.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return DBUtils.db;
	}
	
	public static void closeConnection() {
		if (DBUtils.db != null) DBUtils.db.closeConnection();
	}

}
