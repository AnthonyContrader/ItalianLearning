package it.contrader.dto;


public class OrganizeSentenceDTO {

	private int id;
	private String solution;
	private String sentence;
	private Integer score;
	private String definition;
	private Integer idCategory;
	private String category;
	
	private Integer idLevel;
	private String level;
	
	public OrganizeSentenceDTO() {
		
	}
	
	public OrganizeSentenceDTO(String solution, String sentence, Integer score, String definition, Integer idCategory,
			String category, Integer idLevel, String level) {
		super();
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
	}



	public OrganizeSentenceDTO(int id, String solution, String sentence, Integer score, String definition,
			Integer idCategory, String category, Integer idLevel, String level) {
		super();
		this.id = id;
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
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

	

}
