package it.contrader.converter;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import it.contrader.dto.PlaylistDTO;
import it.contrader.dto.GamePlaylistDTO;

import it.contrader.model.Playlist;
import it.contrader.model.GamePlaylist;

/*
 * created by Enzo, Gabriella
 */

public class PlaylistConverter extends AbstractConverter<Playlist, PlaylistDTO>{
	
	@Autowired
	GamePlaylistConverter gamePlylistConverter = new GamePlaylistConverter();
	
	@Override
	public Playlist toEntity(PlaylistDTO playlistDTO) {
		if(playlistDTO == null)
			return null;
		
		List<GamePlaylist> g = gamePlylistConverter.toEntityList(playlistDTO.getGamePlaylist());
		return new Playlist(playlistDTO.getId(), playlistDTO.getName(), playlistDTO.getDescription(), g);
	}

	@Override
	public PlaylistDTO toDTO(Playlist playlist) {
		if(playlist == null)
			return null;
		
		List<GamePlaylistDTO> g = gamePlylistConverter.toDTOList(playlist.getGamePlaylists());		
		return new PlaylistDTO(playlist.getId(), playlist.getName(), playlist.getDescription(), g);
	}

}
