package it.contrader.dto;

import it.contrader.model.Category;
import it.contrader.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// created By Gabriella Brunetto

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FindAWordDTO {
	
	
		
		
		private Long id;
		
		
		private CategoryDTO category;
		
		
		private LevelDTO level;
		
		
		private String solution;
		
		private String definition;
		
		
		private String sentence;




}
