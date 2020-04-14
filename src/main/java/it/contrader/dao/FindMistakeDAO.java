package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.FindMistake;

public class FindMistakeDAO implements DAO<FindMistake>{
	private final String QUERY_ALL = "SELECT * FROM findMistake";
	private final String QUERY_CREATE = "INSERT INTO findMistake (solution, definition, sentence, optionA, optionB, optionC, score, idCategory) VALUES (?,?,?,?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM findMistake WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE findMistake SET solution=?, definition=?, sentence=?, optionA=?, optionB=?, optionC=?, score=?, idCategory=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM findMistake WHERE id=?";
	
	public List<FindMistake> getAll(){
		List<FindMistake> findMistakeList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			FindMistake findMistake;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				String solution = resultSet.getString("solution");
				String definition = resultSet.getString("definition");
				String sentence = resultSet.getString("sentence");
				String optionA = resultSet.getString("optionA");
				String optionB = resultSet.getString("optionB");
				String optionC = resultSet.getString("optionC");
				Integer score = resultSet.getInt("score");
				Integer idCategory = resultSet.getInt("idCategory");
				findMistake = new FindMistake(id,solution,definition,sentence,optionA,optionB,optionC,score,idCategory); //inizializzo elemento hangman
				findMistakeList.add(findMistake); //aggiungo elemento hangman alla lista
			}
			
		}catch(SQLException e) {
//			e.printStackTrace();
		}
		
		return findMistakeList;
	}
	
	public boolean insert(FindMistake findMistakeToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			preparedStatement.setString(1, findMistakeToInsert.getSolution()); //ora settiamo i parametri della query
			preparedStatement.setString(2, findMistakeToInsert.getDefinition()); //ora settiamo i parametri della query
			preparedStatement.setString(3, findMistakeToInsert.getSentence()); //ora settiamo i parametri della query
			preparedStatement.setString(4, findMistakeToInsert.getOptionA()); //ora settiamo i parametri della query
			preparedStatement.setString(5, findMistakeToInsert.getOptionB()); //ora settiamo i parametri della query
			preparedStatement.setString(6, findMistakeToInsert.getOptionC()); //ora settiamo i parametri della query
			preparedStatement.setInt(7, findMistakeToInsert.getScore()); //ora settiamo i parametri della query
			preparedStatement.setInt(8, findMistakeToInsert.getIdCategory()); //ora settiamo i parametri della query
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
//			e.printStackTrace();
			return false;
		}
	}
	
	// metodo che legge da database il record (riga) corrispondente a categoryId
	public FindMistake read(int findMistakeId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, findMistakeId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			int id = resultSet.getInt("id");
			String solution = resultSet.getString("solution");
			String definition = resultSet.getString("definition");
			String sentence = resultSet.getString("sentence");
			String optionA = resultSet.getString("optionA");
			String optionB = resultSet.getString("optionB");
			String optionC = resultSet.getString("optionC");
			Integer score = resultSet.getInt("score");
			Integer idCategory = resultSet.getInt("idCategory");
			FindMistake findMistake = new FindMistake(id,solution,definition,sentence,optionA,optionB,optionC,score,idCategory);
			return findMistake;
			
		}catch(SQLException e) {
//			e.printStackTrace();
			return null;
		}
	
	}

	public boolean update(FindMistake findMistakeToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (findMistakeToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		FindMistake findMistakeRead = read(findMistakeToUpdate.getId());
		if (findMistakeRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi
		//( categoryRead.equals(categoryToUpdate) == false )
		//( !categoryRead.equals(categoryToUpdate) == true )
		//( categoryRead.equals(categoryToUpdate) != true )
		if ( !findMistakeRead.equals(findMistakeToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (findMistakeToUpdate.getSolution() == null || findMistakeToUpdate.getSolution().equals("")) {
					findMistakeToUpdate.setSolution(findMistakeRead.getSolution());
				}

				if (findMistakeToUpdate.getDefinition() == null || findMistakeToUpdate.getDefinition().equals("")) {
					findMistakeToUpdate.setDefinition(findMistakeRead.getDefinition());
				}
				
				if (findMistakeToUpdate.getSentence() == null || findMistakeToUpdate.getSentence().equals("")) {
					findMistakeToUpdate.setSentence(findMistakeRead.getSentence());
				}
				
				if (findMistakeToUpdate.getOptionA() == null || findMistakeToUpdate.getOptionA().equals("")) {
					findMistakeToUpdate.setOptionA(findMistakeRead.getOptionA());
				}
				
				if (findMistakeToUpdate.getOptionB() == null || findMistakeToUpdate.getOptionB().equals("")) {
					findMistakeToUpdate.setOptionB(findMistakeRead.getOptionB());
				}
				
				if (findMistakeToUpdate.getOptionC() == null || findMistakeToUpdate.getOptionC().equals("")) {
					findMistakeToUpdate.setOptionC(findMistakeRead.getOptionC());
				}
				
				if (findMistakeToUpdate.getScore() == null || findMistakeToUpdate.getScore() <= 0) {
					findMistakeToUpdate.setScore(findMistakeRead.getScore());
				}
				
				if (findMistakeToUpdate.getIdCategory() == null || findMistakeToUpdate.getIdCategory() <= 0) {
					findMistakeToUpdate.setIdCategory(findMistakeRead.getIdCategory());
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, findMistakeToUpdate.getSolution()); //ora settiamo i parametri della query
				preparedStatement.setString(2, findMistakeToUpdate.getDefinition()); //ora settiamo i parametri della query
				preparedStatement.setString(3, findMistakeToUpdate.getSentence()); //ora settiamo i parametri della query
				preparedStatement.setString(4, findMistakeToUpdate.getOptionA()); //ora settiamo i parametri della query
				preparedStatement.setString(5, findMistakeToUpdate.getOptionB()); //ora settiamo i parametri della query
				preparedStatement.setString(6, findMistakeToUpdate.getOptionC()); //ora settiamo i parametri della query
				preparedStatement.setInt(7, findMistakeToUpdate.getScore()); //ora settiamo i parametri della query
				preparedStatement.setInt(8, findMistakeToUpdate.getIdCategory()); //ora settiamo i parametri della query
				preparedStatement.setInt(9, findMistakeToUpdate.getId()); //ora settiamo i parametri della query
				
				int check = preparedStatement.executeUpdate(); //eseguo la query di update (Aggiornamento) del database
				// verifico che l'aggiornamento sia andato a buon fine
				if (check > 0)
					return true;
				else
					return false;
				
				
			}catch(SQLException e){
//				e.printStackTrace();
				return false;
			}
			
		}
		
		return false;

	}

	// metodo che elimina un record (riga) dal database
	public boolean delete(int findMistakeId) {
		
		// controllo che id non sia uguale a 0
		if (findMistakeId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
			preparedStatement.setInt(1, findMistakeId); //setto i parametri nella query
			int check = preparedStatement.executeUpdate(); //eseguo la query
			
			if (check > 0)
				return true;

		}catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
}
