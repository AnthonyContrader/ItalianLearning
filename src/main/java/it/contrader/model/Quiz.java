package it.contrader.model;

public class Quiz {
	

	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String definition;
	private String sentence;
	private Integer idLevel;
	
	public Quiz() {
		
	}

	public Quiz(Integer idCategory, Integer score, String solution, String definition, String sentence,
			Integer idLevel) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}



	public Quiz(int id, Integer idCategory, Integer score, String solution, String definition, String sentence,
			Integer idLevel) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idLevel = idLevel;
	}



	public Quiz(Integer idCategory, Integer score, String solution, String definition, String sentence) {
		
		
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}

	public Quiz(int id, Integer idCategory, Integer score, String solution, String definition, String sentence) {
		
		this.id = id;
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
	
	public Integer getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(Integer idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (id != other.id)
			return false;
		if (idCategory == null) {
			if (other.idCategory != null)
				return false;
		} else if (!idCategory.equals(other.idCategory))
			return false;
		if (idLevel == null) {
			if (other.idLevel != null)
				return false;
		} else if (!idLevel.equals(other.idLevel))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (sentence == null) {
			if (other.sentence != null)
				return false;
		} else if (!sentence.equals(other.sentence))
			return false;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", idCategory=" + idCategory + ", score=" + score + ", solution=" + solution
				+ ", definition=" + definition + ", sentence=" + sentence + ", idLevel=" + idLevel + "]";
	}
	
	
	
	
	
	
}