package it.contrader.dto;

public class QuizDTO {
	
	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String definition;
	private String category;
	private String sentence;
	
	
	
	public QuizDTO() {
	}


	public QuizDTO(int id, Integer idCategory, Integer score, String solution, String definition, String sentence) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		
				
	}


	public QuizDTO(int id, Integer idCategory, Integer score, String solution, String definition, String category, String sentence) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.category = category;
		this.sentence = sentence;
	}


	public QuizDTO(Integer idCategory, Integer score, String solution, String definition,String sentence) {
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
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


	@Override
	public String toString() {
		return "QuizDTO [id=" + id + ", idCategory=" + idCategory + ", score=" + score + ", solution=" + solution
				+ ", definition=" + definition + ", category=" + category + ", sentence=" + sentence + "]";
	}



}
	


