package it.contrader.dto;

import it.contrader.model.Category;
import it.contrader.model.Level;
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
	
	private Long id;
	private String solution;
	private String image;
	
	private Category category;
	private Level level;

}
