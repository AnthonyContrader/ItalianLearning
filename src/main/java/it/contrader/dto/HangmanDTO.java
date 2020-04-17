package it.contrader.dto;

public class HangmanDTO {
	private static final String typeGame = "Hangman";

	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String category;
	private Integer score;
	private Integer idCategory;
	private Integer idLevel;
	private String level;
	
	public HangmanDTO() {
	}
	
	public HangmanDTO(String solution, String definition, String sentence, Integer idCategory, Integer idLevel) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}
	
	public HangmanDTO(String solution, String definition, String sentence, String category, String level) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
		this.level = level;
	}
	
	public HangmanDTO(int id, String solution, String definition, String sentence, Integer idCategory, Integer idLevel) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}
	
	public HangmanDTO(int id, String solution, String definition, String sentence, Integer idCategory, String category, Integer idLevel, String level) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) { 
		this.id = id; 
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getCategory() {
		return category;
	}
	
	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
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


	public void setCategory(String category) {
		this.category = category;
	}

	public static String getTypegame() {
		return typeGame;
	}

}
