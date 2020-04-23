package it.contrader.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Playlist {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private String name;
	
	@Column(name="description", columnDefinition="VARCHAR(1024)")
	private String description;
	
	@OneToMany
	@JoinTable(name = "games_playlists",
			    joinColumns = @JoinColumn(name = "idPlaylist", referencedColumnName = "id"),
			    inverseJoinColumns = @JoinColumn( name = "idGame")
			    )
	@Where(clause = "typeGame = '" + Hangman.typeGame + "'")
    private Set<Hangman> hangmen = new HashSet<>();
}
