package it.contrader.dto;

public class HangmanDTO {

	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String category;
	private Integer score;
	@SuppressWarnings("unused")
	private Integer idCategory;
	
	public HangmanDTO() {
	}
	
	public HangmanDTO(String solution, String definition, String sentence, Integer score, Integer idCategory) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.idCategory = idCategory;
	}
	
	public HangmanDTO(String solution, String definition, String sentence, Integer score, String category) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.category = category;
	}
	
	public HangmanDTO(int id, String solution, String definition, String sentence, Integer score, Integer idCategory) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.idCategory = idCategory;
	}
	
	public HangmanDTO(int id, String solution, String definition, String sentence, Integer score, Integer idCategory, String category) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.category = category;
		this.idCategory = idCategory;
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

	@Override
	public String toString() {
		return id + "\t" + solution + "\t" + sentence + "\t\t" + score + "\t\t" + category;
	}


}
