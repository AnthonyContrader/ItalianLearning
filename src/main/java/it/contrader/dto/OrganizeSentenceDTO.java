package it.contrader.dto;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
**created by Torquato Di Maio
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class OrganizeSentenceDTO {
	
	private static final String typeGame = "OrganizeSentence";
		
	private Long id;
	
	private String solution; 

	private String sentence; //frase disordinata
	
	private String definition;
	
	private CategoryDTO category;
	
	private LevelDTO level;

	public static String getTypeGame() {
		return typeGame;
	}
}
