package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;

import it.contrader.dao.PlaylistDAO;
import it.contrader.dto.GamePlaylistDTO;
import it.contrader.model.GamePlaylist;

public class GamePlaylistConverter implements Converter<GamePlaylist, GamePlaylistDTO> {
	
	private PlaylistDAO playlistDAO;
	
	public GamePlaylistConverter(){
		this.playlistDAO = new PlaylistDAO();
	}

	public GamePlaylistDTO toDTO(GamePlaylist gamePlaylist) {
		String playlistString = playlistDAO.read(gamePlaylist.getIdPlaylist()).getName();
		GamePlaylistDTO gamePlaylistDTO = new GamePlaylistDTO(gamePlaylist.getId(), gamePlaylist.getIdGame() ,gamePlaylist.getIdPlaylist(),playlistString, gamePlaylist.getTypeGame());
		return gamePlaylistDTO;
	}
	public GamePlaylist toEntity(GamePlaylistDTO gamePlaylistDTO) {
		GamePlaylist gamePlaylist = new GamePlaylist(gamePlaylistDTO.getId(), gamePlaylistDTO.getIdGame(), gamePlaylistDTO.getIdPlaylist(), gamePlaylistDTO.getTypeGame());
		return gamePlaylist;
	}
public List<GamePlaylistDTO> toDTOList(List<GamePlaylist> gamePlaylistList){
		
		List<GamePlaylistDTO> gamePlaylistDTOList = new ArrayList<GamePlaylistDTO>();
		
		for (GamePlaylist gamePlaylist : gamePlaylistList) {
			gamePlaylistDTOList.add(toDTO(gamePlaylist));
		}
		return gamePlaylistDTOList;	
		 }
	

}
