package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Level;
import it.contrader.utils.ConnectionSingleton;

public class LevelDAO implements DAO<Level>{
	
	private final String QUERY_ALL = "SELECT * FROM level";
	private final String QUERY_CREATE = "INSERT INTO level (name, description, score) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM level WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE level SET name=?, description=?, score=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM level WHERE id=?";
	
	public List<Level> getAll(){
		List<Level> levelList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			Level level;
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				Integer score = resultSet.getInt("score");
				level = new Level(id, score, name, description); 
				levelList.add(level); 
			}
			
		}catch(SQLException e) {
			//e.printStackTrace();
		}
		
		return levelList;
	}
	
	public boolean insert(Level levelToInsert ) {
		Connection connection = ConnectionSingleton.getInstance(); 
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, levelToInsert.getName());
			preparedStatement.setString(2, levelToInsert.getDescription());
			preparedStatement.setInt(3, levelToInsert.getScore());
			preparedStatement.execute();
			return true;
			
		}catch(SQLException e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public Level read(int levelId) {
		Connection connection = ConnectionSingleton.getInstance();
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, levelId);
			ResultSet resultSet = preparedStatement.executeQuery();

			resultSet.next();
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			int id = resultSet.getInt("id");
			int score = resultSet.getInt("score");
			Level level = new Level(id, score, name, description);
			return level;
			
		}catch(SQLException e) {
			return null;
		}
	
	}

	public boolean update(Level levelToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		if (levelToUpdate.getId() == 0)
			return false;
		
		Level levelRead = read(levelToUpdate.getId());
		if (levelRead == null)
			return false;
		
		if ( !levelRead.equals(levelToUpdate) ) { 
			
			try {
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE); 
				preparedStatement.setString(1, levelToUpdate.getName()); 
				preparedStatement.setString(2, levelToUpdate.getDescription()); 
				preparedStatement.setInt(3, levelToUpdate.getScore());
				preparedStatement.setInt(4, levelToUpdate.getId());
				
				int check = preparedStatement.executeUpdate();
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

	public boolean delete(int levelId) {
		
		if (levelId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE); 
			preparedStatement.setInt(1, levelId);
			int check = preparedStatement.executeUpdate();
			
			if (check > 0)
				return true;

		}catch (SQLException e) {
			return false;
		}
		
		return false;
	}

}
