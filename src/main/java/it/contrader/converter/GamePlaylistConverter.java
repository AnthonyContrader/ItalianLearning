package it.contrader.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.GamePlaylistDTO;
import it.contrader.model.GamePlaylist;

/*
 * created by Torquato Di Maio
 */

@Component
public class GamePlaylistConverter extends AbstractConverter<GamePlaylist, GamePlaylistDTO>{
	
	@Autowired
	PlaylistConverter playlistConverter = new PlaylistConverter();

	@Override
	public GamePlaylist toEntity(GamePlaylistDTO gamePlaylistDTO) {
		GamePlaylist gamePlaylist = null;
		if (gamePlaylistDTO != null) {
			gamePlaylist = new GamePlaylist(gamePlaylistDTO.getId(), gamePlaylistDTO.getTypeGame(),
					gamePlaylistDTO.getIdGame(), playlistConverter.toEntity(gamePlaylistDTO.getPlaylist()));
		}
		return gamePlaylist;
	}

	@Override
	public GamePlaylistDTO toDTO(GamePlaylist gamePlaylist) {
		GamePlaylistDTO gamePlaylistDTO = null;
		if (gamePlaylist != null) {
			gamePlaylistDTO = new GamePlaylistDTO(gamePlaylist.getId(), gamePlaylist.getTypeGame(), 
					gamePlaylist.getIdGame(), playlistConverter.toDTO(gamePlaylist.getPlaylist()));
		}
		return gamePlaylistDTO;
	}

}
