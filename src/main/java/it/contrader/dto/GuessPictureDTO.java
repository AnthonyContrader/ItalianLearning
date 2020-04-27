package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Created by Enzo Tasca
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class GuessPictureDTO {
	
	private static final String typeGame = "GuessPicture";
	
	private Long id;
	private String solution;
	private String image;
	
	private CategoryDTO category;
	private LevelDTO level;

	public static String getTypeGame() {
		return typeGame;
	}
}
