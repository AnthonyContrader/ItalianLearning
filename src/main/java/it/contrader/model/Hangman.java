package it.contrader.model;

public class Hangman {
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private Integer score;
	private Integer idCategory;
	
	public Hangman() {
	}
	
	public Hangman(String solution, String definition, String sentence, Integer score, Integer idCategory) {
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.idCategory = idCategory;
	}
	
	public Hangman(int id, String solution, String definition, String sentence, Integer score, Integer idCategory) {
		this.id = id;
		this.solution = solution;
		this.definition = definition;
		this.sentence = sentence;
		this.score = score;
		this.idCategory = idCategory;
	}

	public int getId() {
		return id;
	}

	/*
	 * public void setId(int id) { this.id = id; }
	 */

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
		if (idCategory != other.idCategory)
			return false;
		if (score != other.score)
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
		return id + "\t" + solution + "\t\t" + definition + "\t\t" + sentence + "\t\t" + score + "\t\t" + idCategory;
	}

}
