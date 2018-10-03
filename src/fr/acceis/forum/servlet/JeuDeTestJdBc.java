package fr.acceis.forum.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.acceis.forum.metier.DBUtils;
import fr.acceis.forum.metier.Datafetcher;

public class JeuDeTestJdBc {

	public final static String[] QUERIES = {
			"drop table Utilisateurs if exists",
			"create table Utilisateurs (id bigint not null, login varchar(255), password varchar(255), primary key (id))",
			"INSERT INTO UTILISATEURS VALUES(1,'admin', 'admin')",
			"INSERT INTO UTILISATEURS VALUES(2,'pierre', 'pierre')",
			"INSERT INTO UTILISATEURS VALUES(3,'paul', 'paul')",
			"INSERT INTO UTILISATEURS VALUES(4,'jacques', 'jacques')",
	};

	public static void main(String[] args) throws Exception {
		/*Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/basejpa", "sa",  "");
		 Statement stmt = connexion.createStatement();

		/*for (String query : QUERIES) {
			stmt.executeUpdate(query);
		}
		

		stmt.close();
		connexion.close();
		 
		 ResultSet result = stmt.executeQuery(
		            "SELECT * FROM Utilisateurs WHERE login = 'admin'");
		 
		 System.out.println("Results : ");
		 while(result.next()) {
			 System.out.println(result.);
		 }
		*/
		Datafetcher db = DBUtils.getDataFetcher();
		System.out.println(db.fetchUser("aze"));

	}

}
