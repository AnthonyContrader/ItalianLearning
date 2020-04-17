package it.contrader.dto;

public class GamePlaylistDTO {
	private int id;
	private Integer idGame;
	private String game;
	private Integer idPlaylist;
	private String playlist;
	private String typeGame;
	
	public GamePlaylistDTO() {
		
	}

	public GamePlaylistDTO(int id, int idGame, String game, int idPlaylist, String playlist, String typeGame) {
		super();
		this.id = id;
		this.idGame = idGame;
		this.game = game;
		this.idPlaylist = idPlaylist;
		this.playlist = playlist;
		this.typeGame = typeGame;
	}

	public GamePlaylistDTO(int idGame, int idPlaylist, String typeGame) {
		super();
		this.idGame = idGame;
		this.idPlaylist = idPlaylist;
		this.typeGame = typeGame;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	public Integer getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}
	
	public String getGame() {
		return game;
	}
	
	public String getPlaylist() {
		return playlist;
	}

	public String getTypeGame() {
		return typeGame;
	}

	public void setTypeGame(String typeGame) {
		this.typeGame = typeGame;
	}

	@Override
	public String toString() {
		return "GameplaylistDTO [id=" + id + ", idGame=" + idGame + ", Playlist=" + playlist + ", typeGame=" + typeGame
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
		GamePlaylistDTO other = (GamePlaylistDTO) obj;
		if (idPlaylist != other.idPlaylist)
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
