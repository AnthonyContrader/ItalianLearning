package it.contrader.dto;

import java.util.ArrayList;
import java.util.List;

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
	
    private List<GamePlaylistDTO> gamePlaylist = new ArrayList<>();

}
