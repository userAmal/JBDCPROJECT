package JDBCProjet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	static Connection con=null;
	public static Connection getConnection() {
		if(con ==null) {
			try {
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demojdbc","root","");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}

}
