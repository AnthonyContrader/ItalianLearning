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
	private final String QUERY_UPDATE = "UPDATE category SET title=?, description=?, WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM category WHERE id=?";
	
		
	public List<Category> getAll(){
		List<Category> categoriesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			Category category;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String description = resultSet.getString("description");
				category = new Category(id,title,description);
				categoriesList.add(category);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return categoriesList;
		
	}

}
