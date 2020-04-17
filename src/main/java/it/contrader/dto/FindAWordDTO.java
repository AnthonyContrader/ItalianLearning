package it.contrader.dto;

public class FindAWordDTO {
	private static final String typeGame = "FindAWord";

	private int id;
	private Integer idCategory;
	private String solution;
	private String definition;
	private String sentence;
	private String category;
	
	private Integer idLevel;
	private String level;
	
	public FindAWordDTO(Integer idCategory, String solution, String definition, String sentence,
			String category, Integer idLevel, String level) {
		super();
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
	}

	public FindAWordDTO(int id, Integer idCategory, String solution, String definition, String sentence,
			String category, Integer idLevel, String level) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
		this.idLevel = idLevel;
		this.level = level;
	}

	public FindAWordDTO(Integer idCategory, String solution, String definition, String sentence,
			String category) {
		super();
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
	}

	public FindAWordDTO(int id, Integer idCategory, String solution, String definition, String sentence,
			String category) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
	}

	public FindAWordDTO()
	{
		
	}
	
	

	public FindAWordDTO(int id, Integer idCategory, String solution, String definition, String sentence,
			Integer idLevel) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}

	public FindAWordDTO(Integer idCategory, String solution, String definition, String sentence,
			Integer idLevel) {
		super();
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}

	public FindAWordDTO(int id, Integer idCategory, String solution, String definition, String sentence,
			String category, Integer idLevel) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.category = category;
		this.idLevel = idLevel;
	}

	public FindAWordDTO(int id, Integer idCategory, String solution, String definition,
			String sentence) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}

	public FindAWordDTO(Integer idCategory, String solution, String definition, String sentence) {
		super();
		this.idCategory = idCategory;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTypeGame() {
		return typeGame;
	}

}

	
	


