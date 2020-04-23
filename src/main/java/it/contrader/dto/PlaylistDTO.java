package it.contrader.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
	
	private Long id;
	private String name;
	private String description;
	
    private Set<GamePlaylistDTO> gamePlaylist = new HashSet<>();

}
