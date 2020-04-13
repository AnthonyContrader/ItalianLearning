package it.contrader.dto;


public class OrganizeSentenceDTO {

	private int id;
	private String solution;
	private String sentence;
	private Integer score;
	private String definition;
	private Integer idCategory;
	private String category;
	
	public OrganizeSentenceDTO() {
		
	}
	
	public OrganizeSentenceDTO(int id, String solution, String sentence, Integer score, String definition,
			Integer idCategory, String category) {
		super();
		this.id = id;
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.category = category;
	}

	
	public OrganizeSentenceDTO(int id, String solution, String sentence, Integer score, String definition,
			Integer idCategory) {
		
		this.id = id;
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
	}
	

	public OrganizeSentenceDTO(String solution, String sentence, Integer score, String definition, Integer idCategory,
			String category) {
		
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.category = category;
	}
	

	public OrganizeSentenceDTO(String solution, String sentence, Integer score, String definition, Integer idCategory) {
		
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
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

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return id + "\t" + solution  + "\t\t" + sentence + "\t\t" + score + "\t\t" + category;
	}
	

}
