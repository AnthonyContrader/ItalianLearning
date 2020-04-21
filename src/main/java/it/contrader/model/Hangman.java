//Created By @Alessandro Alfieri
package it.contrader.model;
import  it.contrader.model.Level;
import it.contrader.model.User.Usertype;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
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
	
	@ManyToOne
	@JoinColumn(name= "idLevel")
	private Level level;
}
