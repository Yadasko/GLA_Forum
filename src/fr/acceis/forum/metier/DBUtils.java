package fr.acceis.forum.metier;

import java.sql.SQLException;

public class DBUtils {
	
	private static Datafetcher db = null;
	
	public static Datafetcher getDataFetcher() {
		if (db == null) {
			try {
				DBUtils.db = new Datafetcher_hsqldb();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return DBUtils.db;
	}

}
