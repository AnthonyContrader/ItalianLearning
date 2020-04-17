package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Playlist;

public class PlaylistDAO implements DAO<Playlist>  {
	
	private final String QUERY_ALL = "SELECT * FROM playlist";
	private final String QUERY_CREATE = "INSERT INTO playlist (name, description) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM playlist WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE playlist SET name=?, description=? WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM playlist WHERE id=?";
	
		
	public List<Playlist> getAll(){
		List<Playlist> categoriesList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			Playlist playlist;
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);	
			
			while(resultSet.next()) { 
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				playlist = new Playlist(id,name,description);
				categoriesList.add(playlist); 
			}
			
		}catch(SQLException e) {}
		
		return categoriesList;
	}
	
	public boolean insert(Playlist playlistToInsert ) {
		Connection connection = ConnectionSingleton.getInstance();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, playlistToInsert.getName());
			preparedStatement.setString(2, playlistToInsert.getDescription());
			preparedStatement.execute();
			return true;
			
		}catch(SQLException e) {
			return false;
		}
	}
	
	public Playlist read(int playlistId) {
		Connection connection = ConnectionSingleton.getInstance(); 
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, playlistId);
			ResultSet resultSet = preparedStatement.executeQuery();

			resultSet.next();
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			int id = resultSet.getInt("id");
			Playlist playlist = new Playlist(id, name, description);
			return playlist;
			
		}catch(SQLException e) {
			return null;
		}
	
	}

	public boolean update(Playlist playlistToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		if (playlistToUpdate.getId() == 0)
			return false;
		
		Playlist playlistRead = read(playlistToUpdate.getId());
		if (playlistRead == null)
			return false;
		
		if ( !playlistRead.equals(playlistToUpdate) ) { 
			
			try {
				
				
				if (playlistToUpdate.getName() == null || playlistToUpdate.getName().equals("")) {
					playlistToUpdate.setName(playlistRead.getName());
				}
				
				
				if (playlistToUpdate.getDescription() == null) {
					playlistToUpdate.setDescription("");
				}
				
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, playlistToUpdate.getName());
				preparedStatement.setString(2, playlistToUpdate.getDescription());
				preparedStatement.setInt(3, playlistToUpdate.getId());
				
				int check = preparedStatement.executeUpdate();
				if (check > 0)
					return true;
				else
					return false;
				
			}catch(SQLException e){
				return false;
			}
			
		}
		return true;
	}

	public boolean delete(int playlistId) {
		
		if (playlistId == 0)
			return false;
		
		Connection connection = ConnectionSingleton.getInstance(); 
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, playlistId);
			int check = preparedStatement.executeUpdate();
			
			if (check > 0)
				return true;

		}catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean find(Integer idPlaylist, Integer idGame, String typeGame) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
