package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.GuessPicture;

public class GuessPictureDAO implements DAO<GuessPicture>{
	
	private final String QUERY_ALL = "SELECT * FROM guessPicture";
	private final String QUERY_CREATE = "INSERT INTO guessPicture (solution, image, idCategory, idLevel) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM guessPicture WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE guessPicture SET solution=?, image=?, idCategory=?, idLevel=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM guessPicture WHERE id=?";
	
	public List<GuessPicture> getAll(){
		List<GuessPicture> guessPictureList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			GuessPicture guessPicture;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				//campi in comune a tutti
				int id = resultSet.getInt("id");
				Integer idCategory = resultSet.getInt("idCategory");
				Integer idLevel = resultSet.getInt("idLevel");
				String solution = resultSet.getString("solution");

				// campi diversi per ogni gioco
				String image = resultSet.getString("image");
				
				guessPicture = new GuessPicture(id, idCategory, solution, image, idLevel);
				guessPictureList.add(guessPicture);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return guessPictureList;
	}
	
	public boolean insert(GuessPicture guessPictureToInsert ) {

		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			
			if(guessPictureToInsert.getIdCategory() < 0 || guessPictureToInsert.getIdLevel() < 0)
				return false;
			
			preparedStatement.setString(1, guessPictureToInsert.getSolution()); 
			preparedStatement.setString(2, guessPictureToInsert.getImage());
			preparedStatement.setInt(3, guessPictureToInsert.getIdCategory()); 
			preparedStatement.setInt(4, guessPictureToInsert.getIdLevel()); 

			preparedStatement.execute();
			return true;
			
		}catch(SQLException e) {
			return false;
		}
	}

	public GuessPicture read(int guessPictureId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, guessPictureId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			String solution = resultSet.getString("solution");
			String image = resultSet.getString("image");
			Integer idLevel = resultSet.getInt("idLevel");
			Integer idCategory = resultSet.getInt("idCategory");
			int id = resultSet.getInt("id");
			
			GuessPicture guessPicture = new GuessPicture(id, idCategory, solution, image, idLevel);
			return guessPicture;
			
		}catch(SQLException e) {
			return null;
		}
	}
	
	public boolean update(GuessPicture guessPictureToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		if (guessPictureToUpdate.getId() == 0)
			return false;
		
		GuessPicture guessPictureRead = read(guessPictureToUpdate.getId());
		if (guessPictureRead == null)
			return false;
		
		if ( !guessPictureRead.equals(guessPictureToUpdate) ) { 
			
			try {
								
				if (guessPictureToUpdate.getSolution() == null || guessPictureToUpdate.getSolution().equals("")) {
					guessPictureToUpdate.setSolution(guessPictureRead.getSolution());
				}

				if (guessPictureToUpdate.getImage() == null || guessPictureToUpdate.getImage().equals("")) {
					guessPictureToUpdate.setImage(guessPictureRead.getImage());
				}
				
				if (guessPictureToUpdate.getIdCategory() == null || guessPictureToUpdate.getIdCategory() < 1 ) {
					guessPictureToUpdate.setIdCategory(guessPictureRead.getIdCategory());
				}
				
				if (guessPictureToUpdate.getIdLevel() == null || guessPictureToUpdate.getIdLevel() < 1 ) {
					guessPictureToUpdate.setIdLevel(guessPictureRead.getIdLevel());
				}
								
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, guessPictureToUpdate.getSolution()); 
				preparedStatement.setString(2, guessPictureToUpdate.getImage()); 
				preparedStatement.setInt(3, guessPictureToUpdate.getIdCategory());
				preparedStatement.setInt(4, guessPictureToUpdate.getIdLevel());
				preparedStatement.setInt(5, guessPictureToUpdate.getId());

				int check = preparedStatement.executeUpdate();
				if (check > 0)
					return true;
				else
					return false;
				
			}catch(SQLException e){
				return false;
			}
		}
		
		return false;

	}
	
	public boolean delete(int guessPictureId) {
		
		if (guessPictureId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
			preparedStatement.setInt(1, guessPictureId); //setto i parametri nella query
			int check = preparedStatement.executeUpdate(); //eseguo la query
			
			if (check > 0)
				return true;

		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
}
