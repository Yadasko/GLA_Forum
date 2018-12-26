package fr.acceis.forum.metier;

public class DBUtils {
	
	private static Datafetcher db = null;
	
	public static Datafetcher getDataFetcher() {
		if (DBUtils.db == null) {
			//DBUtils.db = new Datafetcher_hsqldb();
			DBUtils.db = new Datafetcher_sqlite();
			System.out.println("CREATED OUR DATAFETCHER.");

		}
		return DBUtils.db;
	}
	
	public static void closeConnection() {
		if (DBUtils.db != null) DBUtils.db.closeConnection();
	}

}
