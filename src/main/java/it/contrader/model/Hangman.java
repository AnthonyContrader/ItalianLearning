package it.contrader.model;

public class Hangman {
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private Integer score;
	private Integer idCategory;
	private Integer idLevel;
	
	public Hangman() {
	}
	
	public Hangman(String solution, String definition, String sentence, Integer idCategory, Integer idLevel) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}
	
	public Hangman(int id, String solution, String definition, String sentence, Integer idCategory,Integer idLevel) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}

	public int getId() {
		return id;
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

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public Integer getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(Integer idLevel) {
		this.idLevel = idLevel;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hangman [id=" + id + ", solution=" + solution + ", definition=" + definition + ", sentence=" + sentence
				+ ", score=" + score + ", idCategory=" + idCategory + ", idLevel=" + idLevel + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hangman other = (Hangman) obj;
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
