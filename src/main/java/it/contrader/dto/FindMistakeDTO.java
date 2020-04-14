package it.contrader.dto;

public class FindMistakeDTO {
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String optionA;
	private String optionB;
	private String optionC;
	private String category;
	private Integer score;
	private Integer idCategory;
	
	public FindMistakeDTO() {
		
	}
	
	public FindMistakeDTO(String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer score, String category) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.score = score;
		this.category = category;
	}
	
	public FindMistakeDTO(String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer score, Integer idCategory) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.score = score;
		this.idCategory = idCategory;
	}
	
	public FindMistakeDTO(int id, String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer score, String category) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.score = score;
		this.category = category;
	}
	
	public FindMistakeDTO(int id, String solution, String definition, String sentence, String optionA, String optionB,
			String optionC, Integer score, Integer idCategory) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.score = score;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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
	
	@Override
	public String toString() {
		return id + "\t" + solution + "\t\t"+ sentence + "\t\t"+ score + "\t\t" + category;
	}

}
