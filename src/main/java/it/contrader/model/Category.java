package it.contrader.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/*
 * created by enzo, anna, gabriella, alessandro, torquato
 */

@AllArgsConstructor
@NoArgsConstructor
@Data	//crea getter setter equals hashcode toString
@Entity	//mappa l'entita sul db

public class Category {
	
	@Id //chiave primaria di category
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//genera in automatico l'id ad ogni inserimento
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private String title;
	
	private String description;

}
