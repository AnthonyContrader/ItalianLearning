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
public class CategoryDTO {
	
	Long id;
	String title;
	String description;
	
}
