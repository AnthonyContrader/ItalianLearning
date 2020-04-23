package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.PlaylistDTO;
import it.contrader.model.Playlist;

/*
 * Created by Enzo, Gabriella
 */

@Component
public class PlaylistConverter extends AbstractConverter<Playlist, PlaylistDTO>{
	
	@Override
	public Playlist toEntity(PlaylistDTO playlistDTO) {
		if(playlistDTO == null)
			return null;
		
		return new Playlist(playlistDTO.getId(), playlistDTO.getName(), playlistDTO.getDescription());
	}

	@Override
	public PlaylistDTO toDTO(Playlist playlist) {
		if(playlist == null)
			return null;
		
		return new PlaylistDTO(playlist.getId(), playlist.getName(), playlist.getDescription());
	}

}
