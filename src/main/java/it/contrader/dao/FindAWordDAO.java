package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.FindAWord;


public class FindAWordDAO implements DAO<FindAWord>{
	private final String QUERY_ALL = "SELECT * FROM findAWord";
	private final String QUERY_CREATE = "INSERT INTO findAWord (solution, definition, sentence, idCategory, idLevel) VALUES (?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM findAword WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE findAWord SET solution=?, definition=?, sentence=?, idCategory=?, idLevel=?  WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM findAWord WHERE id=?";
	
	public List<FindAWord> getAll(){
		List<FindAWord> findAWordList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			FindAWord findAWord;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				Integer idCategory = resultSet.getInt("idCategory");
				Integer idLevel = resultSet.getInt("idLevel");
				String solution = resultSet.getString("solution");
				String sentence = resultSet.getString("sentence");
				String definition = resultSet.getString("definition");
				
				findAWord = new FindAWord(id, idCategory, solution, definition, sentence,idLevel); //inizializzo elemento category
				findAWordList.add(findAWord); //aggiungo elemento category alla lista
			}
			
		}catch(SQLException e) {
		//	e.printStackTrace();
		}
		
		return findAWordList;
	}
	public boolean insert(FindAWord findAWordToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			
			if(findAWordToInsert.getIdCategory() < 0 || findAWordToInsert.getIdLevel() < 0)
				return false;
			
			preparedStatement.setString(1, findAWordToInsert.getSolution()); //ora settiamo i parametri della query
			preparedStatement.setString(2, findAWordToInsert.getDefinition());
			preparedStatement.setString(3, findAWordToInsert.getSentence());
			preparedStatement.setInt(4, findAWordToInsert.getIdCategory());//ora settiamo i parametri della query
			preparedStatement.setInt(5, findAWordToInsert.getIdLevel());
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return false;
		}
		
	} 
	public FindAWord read(int findAWordId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, findAWordId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			String solution = resultSet.getString("solution");
			String definition = resultSet.getString("definition");
			String sentence = resultSet.getString("sentence");
			Integer idLevel = resultSet.getInt("idLevel");
			Integer idCategory = resultSet.getInt("idCategory");
			int id = resultSet.getInt("id");
			
			FindAWord findAWord = new FindAWord(id, idCategory, solution, definition, sentence, idLevel);
			return findAWord;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return null;
		}
	}
		public boolean update(FindAWord findAWordToUpdate) {
			Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

			// Check if id is present
			if (findAWordToUpdate.getId() == 0)
				return false;
			
			// preleviamo dal database l'oggetto prima delle modifiche
			FindAWord findAWordRead = read(findAWordToUpdate.getId());
			if (findAWordRead == null)
				return false;
			
			//controllo che gli oggetti siano diversi
			//( findAWordRead.equals(findAWordToUpdate) == false )
			//( !findAWordRead.equals(findAWordToUpdate) == true )
			//( findAWordRead.equals(findAWordToUpdate) != true )
			if ( !findAWordRead.equals(findAWordToUpdate) ) { 
				// gli oggetti sono diversi possiamo aggiornare il record del database
				
				try {
					
					// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
					
					if (findAWordToUpdate.getSolution() == null || findAWordToUpdate.getSolution().equals("")) {
						findAWordToUpdate.setSolution(findAWordRead.getSolution());
					}

					if (findAWordToUpdate.getDefinition() == null) {
						findAWordToUpdate.setDefinition(findAWordRead.getDefinition());
					}
					
					if (findAWordToUpdate.getSentence() == null || findAWordToUpdate.getSentence().equals("")) {
						findAWordToUpdate.setSentence(findAWordRead.getSentence());
					}
					
					if (findAWordToUpdate.getIdCategory() == null || findAWordToUpdate.getIdCategory()<1) {
						findAWordToUpdate.setIdCategory(findAWordRead.getIdCategory());
					}
					if (findAWordToUpdate.getIdLevel() == null || findAWordToUpdate.getIdLevel()<1) {
						findAWordToUpdate.setIdLevel(findAWordRead.getIdLevel());
					}
					
					PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
					preparedStatement.setString(1, findAWordToUpdate.getSolution()); //ora settiamo i parametri della query
					preparedStatement.setString(2, findAWordToUpdate.getDefinition()); //ora settiamo i parametri della query
					preparedStatement.setString(3, findAWordToUpdate.getSentence());
					preparedStatement.setInt(4, findAWordToUpdate.getIdCategory());
					preparedStatement.setInt(5, findAWordToUpdate.getIdLevel());
					preparedStatement.setInt(6, findAWordToUpdate.getId()); //ora settiamo i parametri della query
					
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
		public boolean delete(int findAWordId) {
			
			// controllo che id non sia uguale a 0
			if (findAWordId == 0)
				return false;
			
			Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
			
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
				preparedStatement.setInt(1, findAWordId); //setto i parametri nella query
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



