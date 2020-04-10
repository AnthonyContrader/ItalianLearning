package it.contrader.dto;

public class FindAWordDTO {
	private int id;
	private Integer idCategory;
	private Integer score;
	
	public FindAWordDTO(Integer idCategory, Integer score, String solution, String definition, String sentence,
			String category) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
	}

	public FindAWordDTO(int id, Integer idCategory, Integer score, String solution, String definition, String sentence,
			String category) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
	}
	private String solution;
	private String definition;
	private String sentence;
	private String category;
	
	public FindAWordDTO()
	{
		
	}

	public FindAWordDTO(int id, Integer idCategory, Integer score, String solution, String definition,
			String sentence) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}

	public FindAWordDTO(Integer idCategory, Integer score, String solution, String definition, String sentence) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
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
		return id + "\t" + solution + "\t\t" + definition + "\t\t" + sentence + "\t\t" + score + "\t\t" + category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}

	
	


