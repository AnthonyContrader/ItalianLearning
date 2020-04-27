package it.contrader.model;
/* created by Anna Cecere */


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotNull
	private String solution;
	private String definition;
	@NotNull
	private String sentence;
	
	@ManyToOne
	@JoinColumn(name= "idCategory")
	private Category category;
		
	@ManyToOne
	@JoinColumn(name= "idLevel")
	private Level level;
	
	
}
