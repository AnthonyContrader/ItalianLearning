package it.contrader.converter;
import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.GamePlaylistDTO;
import it.contrader.dto.PlaylistDTO;
import it.contrader.model.GamePlaylist;
import it.contrader.model.Playlist;

public class GamePlaylistConverter implements Converter<GamePlaylist, GamePlaylistDTO> {
	

	public GamePlaylistDTO toDTO(GamePlaylist gamePlaylist) {
		GamePlaylistDTO gamePlaylistDTO = new GamePlaylistDTO(gamePlaylist.getId(), gamePlaylist.getIdGame() , gamePlaylist.getGame(),gamePlaylist.getIdPlaylist(),gamePlaylist.getPlaylist(), gamePlaylist.getTypeGame());
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
