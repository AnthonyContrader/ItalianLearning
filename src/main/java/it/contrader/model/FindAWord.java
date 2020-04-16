package it.contrader.model;

public class FindAWord {
	
	private int id;
	private Integer idCategory;
	private Integer score;
	private Integer idLevel;
	
	private String solution;
	private String definition;
	private String sentence;
	
	public FindAWord()
	{
		
	}

	public FindAWord(int id, Integer idCategory, Integer score, String solution, String definition,
			String sentence, Integer idLevel) {
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.idLevel = idLevel;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}
	
	public FindAWord(Integer idCategory, Integer score, String solution, String definition,
			String sentence, Integer idLevel) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.idLevel = idLevel;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}

	public FindAWord(int id, Integer idCategory, Integer score, String solution, String definition, String sentence) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
	}

	public FindAWord(Integer idCategory, Integer score, String solution, String definition, String sentence) {
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


	public Integer getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(Integer idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public String toString() {
		return "FindAWord [id=" + id + ", idCategory=" + idCategory + ", score=" + score + ", idLevel=" + idLevel
				+ ", solution=" + solution + ", definition=" + definition + ", sentence=" + sentence + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + id;
		result = prime * result + ((idCategory == null) ? 0 : idCategory.hashCode());
		result = prime * result + ((idLevel == null) ? 0 : idLevel.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((sentence == null) ? 0 : sentence.hashCode());
		result = prime * result + ((solution == null) ? 0 : solution.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FindAWord other = (FindAWord) obj;
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

}
	