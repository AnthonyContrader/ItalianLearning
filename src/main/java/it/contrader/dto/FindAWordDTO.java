//created by Gabriella Brunetto

package it.contrader.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
