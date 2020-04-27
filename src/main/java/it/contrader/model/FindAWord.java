//created by Gabriella Brunetto

package it.contrader.model;

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

public class FindAWord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne 
	@JoinColumn(name="idCategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="idLevel")
	private Level level;
	
	@NotNull
	private String solution;
	
	private String definition;
	
	@NotNull
	private String sentence;

}

