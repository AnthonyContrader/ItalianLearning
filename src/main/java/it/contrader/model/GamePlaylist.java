package it.contrader.model;

public class GamePlaylist {
	private int id;
	private Integer idGame;
	private Integer idPlaylist;
	private String typeGame;
	
	public GamePlaylist() {
		
	}

	public GamePlaylist(int id, int idGame, int idPlaylist, String typeGame) {
		super();
		this.id = id;
		this.idGame = idGame;
		this.idPlaylist = idPlaylist;
		this.typeGame = typeGame;
	}

	public GamePlaylist(int idGame, int idPlaylist, String typeGame) {
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

	public String getTypeGame() {
		return typeGame;
	}

	public void setTypeGame(String typeGame) {
		this.typeGame = typeGame;
	}

	@Override
	public String toString() {
		return "Gameplaylist [id=" + id + ", idGame=" + idGame + ", idPlaylist=" + idPlaylist + ", typeGame=" + typeGame
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
		GamePlaylist other = (GamePlaylist) obj;
		if (id != other.id)
			return false;
		if (idGame != other.idGame)
			return false;
		if (idPlaylist != other.idPlaylist)
			return false;
		if (typeGame == null) {
			if (other.typeGame != null)
				return false;
		} else if (!typeGame.equals(other.typeGame))
			return false;
		return true;
	}

}
