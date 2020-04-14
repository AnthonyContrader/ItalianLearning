package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;

import it.contrader.model.OrganizeSentence;

/*id int NOT NULL AUTO_INCREMENT,
solution varchar(32) NOT NULL,
definition varchar(255) NOT NULL, 	
sentence varchar(255) NOT NULL,		
score int NOT NULL,
idCategory int NOT NULL,
FOREIGN KEY (idCategory) REFERENCES category (id),
PRIMARY KEY (id)
*/
public class OrganizeSentenceDAO implements DAO<OrganizeSentence> {

	private final String QUERY_ALL = "SELECT * FROM organizeSentence";
	private final String QUERY_CREATE = "INSERT INTO organizeSentence (solution, definition,sentence,score,idCategory) VALUES (?,?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM organizeSentence WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE organizeSentence SET solution=?, definition=?, sentence=?, score=?, idCategory=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM organizeSentence WHERE id=?";
	
	
	public List<OrganizeSentence> getAll(){
		List<OrganizeSentence> organizeSentenceList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			OrganizeSentence organizeSentence;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				String solution = resultSet.getString("solution");
				String sentence = resultSet.getString("sentence");
				Integer score = resultSet.getInt("score");
				String definition = resultSet.getString("definition");
				Integer idCategory = resultSet.getInt("idCategory");
				organizeSentence = new OrganizeSentence(id,solution,sentence,score,definition,idCategory); //inizializzo elemento category
				organizeSentenceList.add(organizeSentence); //aggiungo elemento category alla lista
			}
			
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		
		return organizeSentenceList;
	}
	
	
	public boolean insert(OrganizeSentence organizeSentenceToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			
			if (organizeSentenceToInsert.getScore() == null || organizeSentenceToInsert.getScore()< 1) {
				organizeSentenceToInsert.setScore(1);
			}
			preparedStatement.setString(1, organizeSentenceToInsert.getSolution()); //ora settiamo i parametri della query
			preparedStatement.setString(2, organizeSentenceToInsert.getDefinition()); //ora settiamo i parametri della query
			preparedStatement.setString(3, organizeSentenceToInsert.getSentence()); //ora settiamo i parametri della query
			preparedStatement.setInt(4, organizeSentenceToInsert.getScore());
			preparedStatement.setInt(5, organizeSentenceToInsert.getIdCategory());
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	
	// metodo che legge da database il record (riga) corrispondente a OrganizeSentenceId
	public OrganizeSentence read(int organizeSentenceId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, organizeSentenceId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			String solution = resultSet.getString("solution");
			String definition = resultSet.getString("definition");
			String sentence = resultSet.getString("sentence");
			
			int id = resultSet.getInt("id");
			int idCategory = resultSet.getInt("idCategory");
			int score = resultSet.getInt("score");
			OrganizeSentence organizeSentence = new OrganizeSentence(id, solution, sentence, score, definition,idCategory);
			return organizeSentence;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	public boolean update(OrganizeSentence organizeSentenceToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (organizeSentenceToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		OrganizeSentence organizeSentenceRead = read(organizeSentenceToUpdate.getId());
		if (organizeSentenceRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi
		//( categoryRead.equals(organizeSentenceToUpdate) == false )
		//( !categoryRead.equals(organizeSentenceToUpdate) == true )
		//( categoryRead.equals(organizeSentenceToUpdate) != true )
		if ( !organizeSentenceRead.equals(organizeSentenceToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (organizeSentenceToUpdate.getSolution() == null || organizeSentenceToUpdate.getSolution().equals("")) {
					organizeSentenceToUpdate.setSolution(organizeSentenceRead.getSolution());
				}

				
				if (organizeSentenceToUpdate.getDefinition() == null || organizeSentenceToUpdate.getDefinition().equals("")) {
					organizeSentenceToUpdate.setDefinition(organizeSentenceRead.getDefinition());
				}
				
				if (organizeSentenceToUpdate.getSentence() == null || organizeSentenceToUpdate.getSentence().equals("")) {
					organizeSentenceToUpdate.setSentence(organizeSentenceRead.getSentence());
				}
				if (organizeSentenceToUpdate.getScore() == null || organizeSentenceToUpdate.getScore() < 1) {
					organizeSentenceToUpdate.setScore(organizeSentenceRead.getScore());
				}
				if (organizeSentenceToUpdate.getIdCategory() == null || organizeSentenceToUpdate.getIdCategory() < 1) {
					organizeSentenceToUpdate.setIdCategory(organizeSentenceRead.getIdCategory());
				}
				
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, organizeSentenceToUpdate.getSolution()); //ora settiamo i parametri della query
				preparedStatement.setString(2, organizeSentenceToUpdate.getDefinition()); //ora settiamo i parametri della query
				preparedStatement.setString(3, organizeSentenceToUpdate.getSentence()); //ora settiamo i parametri della query
				preparedStatement.setInt(4, organizeSentenceToUpdate.getScore()); //ora settiamo i parametri della query
				preparedStatement.setInt(5, organizeSentenceToUpdate.getIdCategory()); //ora settiamo i parametri della query
				preparedStatement.setInt(6, organizeSentenceToUpdate.getId()); //ora settiamo i parametri della query
				
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
		
		return false;

	}
	
	// metodo che elimina un record (riga) dal database
	public boolean delete(int organizeSentenceId) {
		
		// controllo che id non sia uguale a 0
		if (organizeSentenceId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); //preparo la query ma non la eseguo
			preparedStatement.setInt(1, organizeSentenceId); //setto i parametri nella query
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
