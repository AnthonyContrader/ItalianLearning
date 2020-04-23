//Created By @Alessandro Alfieri
package it.contrader.model;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
	
	@Transient
	public static final String typeGame = "Hangman";
	
	@ManyToOne
	@JoinColumn(name= "idCategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name= "idLevel")
	private Level level;

	public static final String getTypeGame() {
		return typeGame;
	}
}
