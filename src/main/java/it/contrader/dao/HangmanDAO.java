package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Hangman;

public class HangmanDAO {
	
	private final String QUERY_ALL = "SELECT * FROM hangman";
	private final String QUERY_CREATE = "INSERT INTO hangman (solution, definition, sentence, score, idCategory) VALUES (?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM hangman WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE hangman SET solution=?, definition=?, sentence=?, score=?, idCategory=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM hangman WHERE id=?";
	
	public List<Hangman> getAll(){
		List<Hangman> hangmenList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			Hangman hangman;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				String solution = resultSet.getString("solution");
				String definition = resultSet.getString("definition");
				String sentence = resultSet.getString("sentence");
				Integer score = resultSet.getInt("score");
				Integer idCategory = resultSet.getInt("idCategory");
				hangman = new Hangman(id,solution,definition,sentence,score,idCategory); //inizializzo elemento hangman
				hangmenList.add(hangman); //aggiungo elemento hangman alla lista
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return hangmenList;
	}
	
	public boolean insert(Hangman hangmanToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			preparedStatement.setString(1, hangmanToInsert.getSolution()); //ora settiamo i parametri della query
			preparedStatement.setString(2, hangmanToInsert.getDefinition()); //ora settiamo i parametri della query
			preparedStatement.setString(3, hangmanToInsert.getSentence()); //ora settiamo i parametri della query
			preparedStatement.setInt(4, hangmanToInsert.getScore()); //ora settiamo i parametri della query
			preparedStatement.setInt(5, hangmanToInsert.getIdCategory()); //ora settiamo i parametri della query
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// metodo che legge da database il record (riga) corrispondente a categoryId
	public Hangman read(int hangmanId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, hangmanId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			int id = resultSet.getInt("id");
			String solution = resultSet.getString("solution");
			String definition = resultSet.getString("definition");
			String sentence = resultSet.getString("sentence");
			Integer score = resultSet.getInt("score");
			Integer idCategory = resultSet.getInt("idCategory");
			Hangman hangman = new Hangman(id,solution,definition,sentence,score,idCategory);
			return hangman;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public boolean update(Hangman hangmanToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (hangmanToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		Hangman hangmanRead = read(hangmanToUpdate.getId());
		if (hangmanRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi
		//( categoryRead.equals(categoryToUpdate) == false )
		//( !categoryRead.equals(categoryToUpdate) == true )
		//( categoryRead.equals(categoryToUpdate) != true )
		if ( !hangmanRead.equals(hangmanToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (hangmanToUpdate.getSolution() == null || hangmanToUpdate.getSolution().equals("")) {
					hangmanToUpdate.setSolution(hangmanRead.getSolution());
				}

				if (hangmanToUpdate.getDefinition() == null || hangmanToUpdate.getDefinition().equals("")) {
					hangmanToUpdate.setDefinition(hangmanRead.getDefinition());
				}
				
				if (hangmanToUpdate.getSentence() == null || hangmanToUpdate.getSentence().equals("")) {
					hangmanToUpdate.setSentence(hangmanRead.getSentence());
				}
				
				if (hangmanToUpdate.getScore() == null || hangmanToUpdate.getScore() <= 0) {
					hangmanToUpdate.setScore(hangmanRead.getScore());
				}
				
				if (hangmanToUpdate.getIdCategory() == null || hangmanToUpdate.getIdCategory() <= 0) {
					hangmanToUpdate.setIdCategory(hangmanRead.getIdCategory());
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, hangmanToUpdate.getSolution()); //ora settiamo i parametri della query
				preparedStatement.setString(2, hangmanToUpdate.getDefinition()); //ora settiamo i parametri della query
				preparedStatement.setString(3, hangmanToUpdate.getSentence()); //ora settiamo i parametri della query
				preparedStatement.setInt(4, hangmanToUpdate.getScore()); //ora settiamo i parametri della query
				preparedStatement.setInt(5, hangmanToUpdate.getIdCategory()); //ora settiamo i parametri della query
				preparedStatement.setInt(6, hangmanToUpdate.getId()); //ora settiamo i parametri della query
				
				int check = preparedStatement.executeUpdate(); //eseguo la query di update (Aggiornamento) del database
				// verifico che l'aggiornamento sia andato a buon fine
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

	// metodo che elimina un record (riga) dal database
	public boolean delete(int categoryId) {
		
		// controllo che id non sia uguale a 0
		if (categoryId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
			preparedStatement.setInt(1, categoryId); //setto i parametri nella query
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
