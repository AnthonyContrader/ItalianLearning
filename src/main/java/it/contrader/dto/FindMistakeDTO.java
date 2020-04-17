package it.contrader.dto;

public class FindMistakeDTO {
	private static final String typeGame = "FindMistake";

	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String optionA;
	private String optionB;
	private String optionC;
	private String category;
	private Integer idCategory;
	
	private Integer idLevel;
	private String level;
	
	public FindMistakeDTO() {
		
	}
	
	public FindMistakeDTO(String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, String category, String level) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.category = category;
		this.level = level;
	}
	
	public FindMistakeDTO(String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer idCategory, Integer idLevel) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}
	
	public FindMistakeDTO(int id, String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer idCategory, String category, Integer idLevel, String level) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.category = category;
		this.idCategory = idCategory;
		this.level = level;
		this.idLevel = idLevel;
	}
	
	public FindMistakeDTO(int id, String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer idCategory, Integer idLevel) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
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

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategory(Integer idCategory) {
		return category; 
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
