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

/*
 * created by enzo tasca
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class GuessPicture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String image;
	
	@NotNull
	private String solution;
	
	/*
	 * idCategory relaziona il gioco alla categoria e tramite
	 * questo nella variabile category avremo tutti i campi
	 * relativi alla categoria, lo stesso discorso vale per il livello
	 */
	@ManyToOne
	@JoinColumn(name= "idCategory")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name= "idLevel")
	private Level level;
	
}
