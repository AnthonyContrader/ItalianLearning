package it.contrader.dto;

public class GameplaylistDTO {
	private int id;
	private int idGame;
	private int Playlist;
	private String typeGame;
	
	public GameplaylistDTO() {
		
	}

	public GameplaylistDTO(int id, int idGame, int playlist, String typeGame) {
		super();
		this.id = id;
		this.idGame = idGame;
		Playlist = playlist;
		this.typeGame = typeGame;
	}

	public GameplaylistDTO(int idGame, int playlist, String typeGame) {
		super();
		this.idGame = idGame;
		Playlist = playlist;
		this.typeGame = typeGame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public int getPlaylist() {
		return Playlist;
	}

	public void setPlaylist(int playlist) {
		Playlist = playlist;
	}

	public String getTypeGame() {
		return typeGame;
	}

	public void setTypeGame(String typeGame) {
		this.typeGame = typeGame;
	}

	@Override
	public String toString() {
		return "GameplaylistDTO [id=" + id + ", idGame=" + idGame + ", Playlist=" + Playlist + ", typeGame=" + typeGame
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameplaylistDTO other = (GameplaylistDTO) obj;
		if (Playlist != other.Playlist)
			return false;
		if (id != other.id)
			return false;
		if (idGame != other.idGame)
			return false;
		if (typeGame == null) {
			if (other.typeGame != null)
				return false;
		} else if (!typeGame.equals(other.typeGame))
			return false;
		return true;
	}
	

}
