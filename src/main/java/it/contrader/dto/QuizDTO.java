package it.contrader.dto;

public class QuizDTO {
	private static final String typeGame = "Quiz";
	
	private int id;
	private Integer idCategory;
	private String solution;
	private String definition;
	private String category;
	private String sentence;
	private Integer idLevel;
	private String level;
	
	public QuizDTO() {
	}

	public QuizDTO(int id, Integer idCategory, String solution, String definition, String sentence,Integer idLevel) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}

	public QuizDTO(Integer idCategory,  String solution, String definition,String sentence, Integer idLevel) {
		
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}

	public QuizDTO(Integer idCategory, String solution, String definition, String category,
			String sentence, Integer idLevel, String level) {
		super();
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.category = category;
		this.sentence = sentence;
		this.idLevel = idLevel;
		this.level = level;
	}

	public QuizDTO(int id, Integer idCategory, String solution, String definition, String category,
			String sentence, Integer idLevel, String level) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.category = category;
		this.sentence = sentence;
		this.idLevel = idLevel;
		this.level = level;
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


	public QuizDTO(int id, Integer idCategory, String solution, String definition, String sentence) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;	
	}

	public QuizDTO(int id, Integer idCategory, String solution, String definition, String category, String sentence) {
		
		this.id = id;
		this.idCategory = idCategory;
		this.solution = solution;
		this.definition = definition;
		this.category = category;
		this.sentence = sentence;
	}


	public QuizDTO(Integer idCategory, String solution, String definition,String sentence) {
		this.idCategory = idCategory;
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
	
	public String getTypeGame() {
		return typeGame;
	}

}
	