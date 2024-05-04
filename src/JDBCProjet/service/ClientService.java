package JDBCProjet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBCProjet.connexion.Client;
import JDBCProjet.connexion.Connexion;
import JDBCProjet.dao.IDao;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {

		String query = "INSERT INTO client(nom,prenom) VALUES( '" + o.getNom() + "','" + o.getPrenom() + "')";
		try {
			Statement stmt = Connexion.getConnection().createStatement();
			int nb1 = stmt.executeUpdate(query);
			if (nb1 > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println("Error creating SQL statement: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Client> findAll() {
		try {
			Connection conn = Connexion.getConnection();
			Statement stmt = conn.createStatement();
			try {
				List<Client> c = new ArrayList<>();
				String query = "SELECT * from client";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					c.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3)));

				}
				return c;
			} catch (SQLException e) {
				System.err.println("Error executing query: " + e.getMessage());
				return null;
			}

		} catch (SQLException e) {
			System.err.println("Error creating SQL statement: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Client findById(int id) {
			
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * from client WHERE id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			return new Client(rs.getInt(1),rs.getString(2),rs.getString(3));
			return null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
			
		}
			
	}

	@Override
	public boolean delete(Client c) {
		
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from client WHERE id=?");
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
			
		}
	}

	@Override
	public boolean update(Client c) {
		try {
			Connection conn = Connexion.getConnection();
			PreparedStatement stmt = conn.prepareStatement("Update client set nom=? WHERE id=?");
			
			stmt.setString(1, c.getNom());
			stmt.setInt(2, c.getId());
			
			stmt.executeUpdate();
			return true;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
			
		}
	}

}
