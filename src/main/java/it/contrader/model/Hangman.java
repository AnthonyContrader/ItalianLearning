//Created By @Alessandro Alfieri
package it.contrader.model;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hangman {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String solution;
	
	private String definition;
	
	@NotNull
	private String sentence;
	
	@ManyToOne
	@JoinColumn(name= "idcategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name= "idlevel")
	private Level level;
}
