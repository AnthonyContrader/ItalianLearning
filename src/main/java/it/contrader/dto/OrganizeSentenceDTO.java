package it.contrader.dto;


import it.contrader.model.Category;
import it.contrader.model.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
**created by Torquato Di Maio
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizeSentenceDTO {
	
	private Long id;
	
	private String solution; 

	private String sentence; //frase disordinata
	
	private String definition;
	
	private CategoryDTO category;
	
	private LevelDTO level;

}
