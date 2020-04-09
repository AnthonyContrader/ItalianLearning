package it.contrader.dto;

public class QuizDTO {
	
	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String definition;
	
	
	
	
	public QuizDTO() {
	}


	public QuizDTO(int id, Integer idCategory, Integer score, String solution, String definition) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
	}


	public QuizDTO(Integer idCategory, Integer score, String solution, String definition) {
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
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
	
	@Override
	public String toString() {
		return id + "\t" + solution + "\t\t" + definition  + "\t\t" + score + "\t\t" + idCategory;
	}
	

}
