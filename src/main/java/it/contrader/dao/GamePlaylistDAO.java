package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.GamePlaylist;

public class GamePlaylistDAO implements DAO<GamePlaylist>{
	
	private final String QUERY_ALL = "SELECT * FROM category";
	private final String QUERY_CREATE = "INSERT INTO category (idGame, idPlaylist, typeGame) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM category WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE category SET idGame=?, idPlaylist=?, typeGame=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM category WHERE id=?";
	
		
	public List<GamePlaylist> getAll(){
		List<GamePlaylist> gamePlaylistList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			GamePlaylist gamePlaylist;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				Integer idGame = resultSet.getInt("idGame");
				Integer idPlaylist = resultSet.getInt("idPlaylist");
				String typeGame = resultSet.getString("typeGame");
				gamePlaylist = new GamePlaylist(id, idGame, idPlaylist, typeGame); //inizializzo elemento category
				gamePlaylistList.add(gamePlaylist); //aggiungo elemento category alla lista
			}
			
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		
		return gamePlaylistList;
	}
	
	public boolean insert(GamePlaylist gamePlaylistToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, gamePlaylistToInsert.getIdGame()); //ora settiamo i parametri della query
			preparedStatement.setInt(2, gamePlaylistToInsert.getIdPlaylist()); //ora settiamo i parametri della query
			preparedStatement.setString(3, gamePlaylistToInsert.getTypeGame()); //ora settiamo i parametri della query
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	// metodo che legge da database il record (riga) corrispondente a categoryId
	public GamePlaylist read(int gamePlaylistId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, gamePlaylistId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			Integer idGame = resultSet.getInt("idGame");
			Integer idPlaylist = resultSet.getInt("idPlaylist");
			String typeGame = resultSet.getString("typeGame");
			int id = resultSet.getInt("id");
			GamePlaylist gamePlaylist = new GamePlaylist(id, idGame, idPlaylist, typeGame);
			return gamePlaylist;
			
		}catch(SQLException e) {
			return null;
		}
	
	}

	public boolean update(GamePlaylist gamePlaylistToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (gamePlaylistToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		GamePlaylist gamePlaylistRead = read(gamePlaylistToUpdate.getId());
		if (gamePlaylistRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi
		//( categoryRead.equals(categoryToUpdate) == false )
		//( !categoryRead.equals(categoryToUpdate) == true )
		//( categoryRead.equals(categoryToUpdate) != true )
		if ( !gamePlaylistRead.equals(gamePlaylistToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (gamePlaylistToUpdate.getIdGame() == null || gamePlaylistToUpdate.getIdGame() <= 0) {
					gamePlaylistToUpdate.setIdGame(gamePlaylistRead.getIdGame());
				}
				
				if (gamePlaylistToUpdate.getIdPlaylist() == null || gamePlaylistToUpdate.getIdPlaylist() <= 0) {
					gamePlaylistToUpdate.setIdPlaylist(gamePlaylistRead.getIdPlaylist());
				}
				
				if (gamePlaylistToUpdate.getTypeGame() == null || gamePlaylistToUpdate.getTypeGame().equals("")) {
					gamePlaylistToUpdate.setTypeGame(gamePlaylistRead.getTypeGame());
				}
				
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setInt(1, gamePlaylistToUpdate.getIdGame()); //ora settiamo i parametri della query
				preparedStatement.setInt(2, gamePlaylistToUpdate.getIdPlaylist()); //ora settiamo i parametri della query
				preparedStatement.setString(3, gamePlaylistToUpdate.getTypeGame()); //ora settiamo i parametri della query
				preparedStatement.setInt(4, gamePlaylistToUpdate.getId()); //ora settiamo i parametri della query
				
				int check = preparedStatement.executeUpdate(); //eseguo la query di update (Aggiornamento) del database
				// verifico che l'aggiornamento sia andato a buon fine
				if (check > 0)
					return true;
				else
					return false;
				
				
			}catch(SQLException e){
				//e.printStackTrace();
				return false;
			}
			
		}
		
		return true;

	}

	// metodo che elimina un record (riga) dal database
	public boolean delete(int gamePlaylistId) {
		
		// controllo che id non sia uguale a 0
		if (gamePlaylistId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
			preparedStatement.setInt(1, gamePlaylistId); //setto i parametri nella query
			int check = preparedStatement.executeUpdate(); //eseguo la query
			
			if (check > 0)
				return true;

		}catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
}
