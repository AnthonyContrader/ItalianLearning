package it.contrader.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * created by Torquato Di Maio
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class GamePlaylistDTO {

	
	private Long id;
	
	private String TypeGame;
	
	private Long idGame;
	
	private PlaylistDTO playlist;
}
