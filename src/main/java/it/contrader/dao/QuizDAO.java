package it.contrader.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Quiz;

public class QuizDAO {

	private final String QUERY_ALL = "SELECT * FROM Quiz";
	private final String QUERY_CREATE = "INSERT INTO Quiz (solution, definition, score, idCategory) VALUES (?,?,?,?)";
	private final String QUERY_READ = "SELECT * FROM Quiz WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE Quiz SET solution=?, definition=?, score =?, idCategory=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM Quiz WHERE id=?";
	
	
	public List<Quiz> getAll(){
		List<Quiz> quizList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance(); 
	
		
		try {
			
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			
			while(resultSet.next()) { 
				int id = resultSet.getInt("id");
				int idCategory = resultSet.getInt("idCategory");
				int score = resultSet.getInt("score");
				String solution= resultSet.getString("solution");		
			    String definition = resultSet.getString("definition");
				Quiz quiz = new Quiz (solution, definition, id, idCategory, score); 
				quizList.add(quiz);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return quizList;
	    }
	public boolean insert (Quiz quizToInsert) {
		Connection connection = ConnectionSingleton.getInstance(); 
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1,quizToInsert.getSolution());
			preparedStatement.setString(2,quizToInsert.getDefinition());
		 	preparedStatement.setInt(3,quizToInsert.getScore());
			preparedStatement.setInt(4,quizToInsert.getIdCategory());
			preparedStatement.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public Quiz read(int QuizId) {
		Connection connection = ConnectionSingleton.getInstance(); 
	
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ); 		
			preparedStatement.setInt(1, QuizId);
			ResultSet resultSet = preparedStatement.executeQuery(); 

			resultSet.next();
			String solution = resultSet.getString("solution");
			String definition = resultSet.getString("definition");
			int id = resultSet.getInt("id");
			Integer score = resultSet.getInt("score");
			Integer idCategory = resultSet.getInt("IdCategory");
			Quiz quiz = new Quiz (solution, definition, id, idCategory, score); 
        return quiz;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean update(Quiz quizToUpdate) {
		Connection connection = ConnectionSingleton.getInstance(); 

		
		if (quizToUpdate.getId() == 0)
			return false;
		
		
		Quiz quizRead = read(quizToUpdate.getId());
		if (quizRead == null)
			return false;
		
		
		if ( !quizRead.equals(quizToUpdate) ) { 
			
			try {
				
				
				
				if (quizToUpdate.getSolution() == null || quizToUpdate.getSolution().equals("")) {
					quizToUpdate.setSolution(quizRead.getSolution());
				}

				if (quizToUpdate.getDefinition() == null || quizToUpdate.getDefinition().equals("")) {
					quizToUpdate.setDefinition(quizRead.getDefinition());
				}
				if (quizToUpdate.getScore() == null || quizToUpdate.getScore()<1 ) {
					quizToUpdate.setScore(1);
				}
				if (quizToUpdate.getIdCategory() == null || quizToUpdate.getIdCategory()<1 ) {
					quizToUpdate.setIdCategory(quizRead.getIdCategory());
				}
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement (QUERY_UPDATE); 
				preparedStatement.setString(1,quizToUpdate.getSolution());
				preparedStatement.setString(2,quizToUpdate.getDefinition());
			 	preparedStatement.setInt(3,quizToUpdate.getScore());
				preparedStatement.setInt(4,quizToUpdate.getIdCategory());
				int check = preparedStatement.executeUpdate();
				
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

	public boolean delete(int quizId) {
		
		
		if (quizId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); 
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, quizId); 
			int check = preparedStatement.executeUpdate(); 			
			if (check > 0)
				return true;

		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	return false;

	}
}

