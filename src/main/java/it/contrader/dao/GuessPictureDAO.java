package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.GuessPicture;

/*
 	id int NOT NULL AUTO_INCREMENT,
	solution varchar(32) NOT NULL,
    image text(65535) NOT NULL,
    score int NOT NULL,
    idCategory int NOT NULL,
 */

public class GuessPictureDAO {
	
	private final String QUERY_ALL = "SELECT * FROM guessPicture";
	private final String QUERY_CREATE = "INSERT INTO guessPicture (solution, image, score ,idCategory) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM guessPicture WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE guessPicture SET solution=?, image=?, score=?, idCategory=? WHERE id=?";
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
				Integer score = resultSet.getInt("score");
				String solution = resultSet.getString("solution");

				// campi diversi per ogni gioco
				String image = resultSet.getString("image");
				
				guessPicture = new GuessPicture(id, idCategory, score, solution, image) ; //inizializzo elemento 
				guessPictureList.add(guessPicture); //aggiungo elemento alla lista
			}
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
		return guessPictureList;
	}
	
	public boolean insert(GuessPicture guessPictureToInsert ) {

		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			
			if (guessPictureToInsert.getScore() == null || guessPictureToInsert.getScore() < 1 ) {
				guessPictureToInsert.setScore(1);
			}
			
			//ora settiamo i parametri della query
			preparedStatement.setString(1, guessPictureToInsert.getSolution()); 
			preparedStatement.setString(2, guessPictureToInsert.getImage());
			preparedStatement.setInt(3, guessPictureToInsert.getScore()); 
			preparedStatement.setInt(4, guessPictureToInsert.getIdCategory()); 

			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			//e.printStackTrace();
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
			Integer score = resultSet.getInt("score");
			Integer idCategory = resultSet.getInt("idCategory");
			int id = resultSet.getInt("id");
			
			GuessPicture guessPicture = new GuessPicture(id, idCategory, score, solution, image);
			return guessPicture;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(GuessPicture guessPictureToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (guessPictureToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		GuessPicture guessPictureRead = read(guessPictureToUpdate.getId());
		if (guessPictureRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi 
		if ( !guessPictureRead.equals(guessPictureToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (guessPictureToUpdate.getSolution() == null || guessPictureToUpdate.getSolution().equals("")) {
					guessPictureToUpdate.setSolution(guessPictureRead.getSolution());
				}

				if (guessPictureToUpdate.getImage() == null || guessPictureToUpdate.getImage().equals("")) {
					guessPictureToUpdate.setImage(guessPictureRead.getImage());
				}
				
				if (guessPictureToUpdate.getScore() == null || guessPictureToUpdate.getScore() < 1 ) {
					guessPictureToUpdate.setScore(guessPictureRead.getScore());
				}
				
				if (guessPictureToUpdate.getIdCategory() == null || guessPictureToUpdate.getIdCategory() < 1 ) {
					guessPictureToUpdate.setIdCategory(guessPictureRead.getIdCategory());
				}
								
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, guessPictureToUpdate.getSolution()); 
				preparedStatement.setString(2, guessPictureToUpdate.getImage()); 
				preparedStatement.setInt(3, guessPictureToUpdate.getScore());
				preparedStatement.setInt(4, guessPictureToUpdate.getIdCategory());
				preparedStatement.setInt(5, guessPictureToUpdate.getId());

				int check = preparedStatement.executeUpdate(); //eseguo la query di update (Aggiornamento) del database
				if (check > 0)
					return true;
				else
					return false;
				
			}catch(SQLException e){
				e.printStackTrace();
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
