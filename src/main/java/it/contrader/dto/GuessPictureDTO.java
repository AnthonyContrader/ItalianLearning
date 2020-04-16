package it.contrader.dto;

public class GuessPictureDTO {
	private static final String typeGame = "GuessPicture";

	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String image;
	private String category;
	
	private Integer idLevel;
	private String level;
	
	public GuessPictureDTO() {}
	
	
	public GuessPictureDTO(Integer idCategory, Integer score, String solution, String image, String category,
			Integer idLevel, String level) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
	}



	public GuessPictureDTO(int id, Integer idCategory, Integer score, String solution, String image, String category,
			Integer idLevel, String level) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
	}



	public GuessPictureDTO(int id, Integer idCategory, Integer score, String solution, String image) {
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
	}

	public GuessPictureDTO(Integer idCategory, Integer score, String solution, String image) {
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
	}

	public GuessPictureDTO(Integer idCategory, Integer score, String solution, String image, String category) {
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
		this.category = category;
	}

	public GuessPictureDTO(int id, Integer idCategory, Integer score, String solution, String image, String category) {
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public Integer getIdLevel() {
		return idLevel;
	}


	public void setIdLevel(Integer idLevel) {
		this.idLevel = idLevel;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}

	public static String getTypegame() {
		return typeGame;
	}
	
}
