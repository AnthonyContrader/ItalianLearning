package it.contrader.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/*
 * created by Torquato Di Maio
 */

@AllArgsConstructor
@NoArgsConstructor
@Data	
@Entity
public class GamePlaylist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String typeGame;
	@NotNull
	private Long idGame;
	
	@ManyToOne
	@JoinColumn(name="IdPlaylist")
	private Playlist playlist;
	
}
