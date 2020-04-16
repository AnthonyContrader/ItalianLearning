package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.PlaylistDTO;
import it.contrader.model.Playlist;

public class PlaylistConverter  implements Converter<Playlist, PlaylistDTO> {

	public PlaylistDTO toDTO(Playlist playlist) {
		PlaylistDTO playlistDTO = new PlaylistDTO(playlist.getId(), playlist.getName() ,playlist.getDescription());
		return playlistDTO;
	}
	
	public Playlist toEntity(PlaylistDTO playlistDto) {
		Playlist playlist = new Playlist(playlistDto.getId(), playlistDto.getName(), playlistDto.getDescription());
		return playlist;
	}
	
	public List<PlaylistDTO> toDTOList(List<Playlist> playlistList){
		
		List<PlaylistDTO> playlistDTOList = new ArrayList<PlaylistDTO>();
		
		for (Playlist playlist : playlistList) {
			playlistDTOList.add(toDTO(playlist));
		}
		return playlistDTOList;

	}
	
}
