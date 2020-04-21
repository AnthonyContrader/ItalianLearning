//Created By @Alessandro Alfieri
package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Integer score;
}
