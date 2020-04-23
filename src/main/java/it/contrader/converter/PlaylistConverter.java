package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;

import it.contrader.dto.PlaylistDTO;
import it.contrader.model.Playlist;

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
		
		//Category category = categoryConverter.toEntity(playlistDTO.getCategory());
		return new Playlist(playlistDTO.getId(), playlistDTO.getName(), playlistDTO.getDescription(), null);
	}

	@Override
	public PlaylistDTO toDTO(Playlist guessPicture) {
		if(guessPicture == null)
			return null;
		
		//CategoryDTO category = categoryConverter.toDTO(guessPicture.getCategory());
		return new PlaylistDTO(guessPicture.getId(), guessPicture.getName(), guessPicture.getDescription(), null);
	}

}
