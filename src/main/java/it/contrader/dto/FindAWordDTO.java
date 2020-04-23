package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// created By Gabriella Brunetto

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FindAWordDTO {
		private static final String typeGame = "FindAWord";
	
		private Long id;
		
		private CategoryDTO category;
		
		private LevelDTO level;
		
		private String solution;
		
		private String definition;
		
		private String sentence;

		public static String getTypeGame() {
			return typeGame;
		}
}
