package it.contrader.dto;



import it.contrader.model.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamePlaylistDTO {

	
	private Long id;
	
	private String TypeGame;
	
	private Long idGame;
	
	private Playlist playlist;
}
