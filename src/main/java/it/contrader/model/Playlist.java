package it.contrader.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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
	
	public Playlist(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Playlist(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	@NotNull
	private String name;
	
	@Column(name="description", columnDefinition="VARCHAR(1024)")
	private String description;
	
	
	@OneToMany(mappedBy = "playlist",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<GamePlaylist> gamePlaylists = new ArrayList<>();
	 
	
}
