package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Created by Enzo Tasca
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
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
