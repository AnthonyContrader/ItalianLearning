package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Created By Enzo, Gabriella 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class PlaylistDTO {
	
	private Long id;
	private String name;
	private String description;
	
    //private List<GamePlaylistDTO> gamePlaylist = new ArrayList<>();

}
