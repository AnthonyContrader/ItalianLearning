package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Created By Enzo, Gabriella 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
	
	private Long id;
	private String name;
	private String description;
	
    //private List<GamePlaylistDTO> gamePlaylist = new ArrayList<>();

}
