package fr.acceis.forum.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hsqldb.rights.User;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;

public class JeuDeTestJdBc {

	public final static String[] QUERIES = {
			"drop table Messages if exists",
			"create table Messages (id bigint not null, author varchar(255), content varchar(255), thread_id bigint not null, primary key (id))",
			"INSERT INTO Messages VALUES(1, 'admin', 'Tomcat is bae', 1)",
			"INSERT INTO Messages VALUES(2, 'pierre', 'I prefer PHP (don t burn me pls).', 1)",
			"INSERT INTO Messages VALUES(3, 'admin', 'As long as you don t use Javascript we are fine!', 1)",
			"INSERT INTO Messages VALUES(4, 'admin', 'Yes. End of discussion', 2)",
			"INSERT INTO Messages VALUES(5, 'jacques', 'PASTAAAAAAAAAAS', 3)"
	};

	public static void main(String[] args) throws Exception {
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:D:/M2/GLA/TP1/Servers/forum/data/", "sa",  "");
		 Statement stmt = connexion.createStatement();

		for (String query : QUERIES) {
			stmt.executeUpdate(query);
		}
		

		stmt.close();
		connexion.close();
		 
		
		System.out.println("END.");
	

	}

}
