package it.contrader.model;

import javax.persistence.Column;
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
**created by torquato di maio
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrganizeSentence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String solution; 
	@NotNull
	private String sentence; //frase disordinata
	
	private String definition;
	
	@ManyToOne
	@JoinColumn(name="IdCategory") //molti giochi si relazionano ad una categoria. il gioco si va a relazionare con category tramite idCategory
	private Category category; //dentro category ci sara titolo,descrizione ecc.. della categoria(tutti i campi di categoria)
	
	@ManyToOne
	@JoinColumn(name="IdLevel")
	private Level level;
	
}
