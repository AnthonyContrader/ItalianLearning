package it.contrader.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * created by Enzo, Gabriella
 */

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
		
	@OneToMany(mappedBy = "playlist")
    private Set<GamePlaylist> gamePlaylists = new HashSet<>();
	
}
