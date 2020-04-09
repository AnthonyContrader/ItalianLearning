package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Category;

/*
 * 	id int NOT NULL AUTO_INCREMENT,
    title varchar(32) NOT NULL,
    description varchar(512) DEFAULT NULL,
 */


public class CategoryDAO {
	
	private final String QUERY_ALL = "SELECT * FROM category";
	private final String QUERY_CREATE = "INSERT INTO category (title, description) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM category WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE category SET title=?, description=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM category WHERE id=?";
	
		
	public List<Category> getAll(){
		List<Category> categoriesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			Category category;
			Statement statement = connection.createStatement(); //crea connessione con database
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	//ci permette di eseguire una query con il database
			
			while(resultSet.next()) { // legge ogni riga restituita dal database
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				category = new Category(id,title,description); //inizializzo elemento category
				categoriesList.add(category); //aggiungo elemento category alla lista
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return categoriesList;
	}
	
	public boolean insert(Category categoryToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE); //oggetto che prepara una query senza eseguirla
			preparedStatement.setString(1, categoryToInsert.getTitle()); //ora settiamo i parametri della query
			preparedStatement.setString(2, categoryToInsert.getDescription()); //ora settiamo i parametri della query
			preparedStatement.execute(); //eseguo la query
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// metodo che legge da database il record (riga) corrispondente a categoryId
	public Category read(int categoryId) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); //oggetto che prepara una query senza eseguirla
			preparedStatement.setInt(1, categoryId); //ora settiamo i parametri della query
			ResultSet resultSet = preparedStatement.executeQuery(); //eseguo la query

			resultSet.next();
			String title = resultSet.getString("title");
			String description = resultSet.getString("description");
			int id = resultSet.getInt("id");
			Category category = new Category(id, title, description);
			return category;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}

	public boolean update(Category categoryToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); //definisco la connessione al database

		// Check if id is present
		if (categoryToUpdate.getId() == 0)
			return false;
		
		// preleviamo dal database l'oggetto prima delle modifiche
		Category categoryRead = read(categoryToUpdate.getId());
		if (categoryRead == null)
			return false;
		
		//controllo che gli oggetti siano diversi
		//( categoryRead.equals(categoryToUpdate) == false )
		//( !categoryRead.equals(categoryToUpdate) == true )
		//( categoryRead.equals(categoryToUpdate) != true )
		if ( !categoryRead.equals(categoryToUpdate) ) { 
			// gli oggetti sono diversi possiamo aggiornare il record del database
			
			try {
				
				// se il titolo o la descrizione dell'oggetto modificato sono vuoti o nulli prendo i valori antecedenti alla modifica
				
				if (categoryToUpdate.getTitle() == null || categoryToUpdate.getTitle().equals("")) {
					categoryToUpdate.setTitle(categoryRead.getTitle());
				}

				if (categoryToUpdate.getDescription() == null || categoryToUpdate.getDescription().equals("")) {
					categoryToUpdate.setDescription(categoryRead.getDescription());
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); //preparo la query ma non la eseguo
				preparedStatement.setString(1, categoryToUpdate.getTitle()); //ora settiamo i parametri della query
				preparedStatement.setString(2, categoryToUpdate.getDescription()); //ora settiamo i parametri della query
				preparedStatement.setInt(3, categoryToUpdate.getId()); //ora settiamo i parametri della query
				
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
