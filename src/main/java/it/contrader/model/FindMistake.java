package it.contrader.model;

public class FindMistake {
	private int id;
	private String solution;
	private String definition;
	private String sentence;
	private String optionA;
	private String optionB;
	private String optionC;
	private Integer score;
	private Integer idCategory;
	
	public FindMistake() {
		
	}
	
	public FindMistake(String solution, String definition, String sentence, String optionA, String optionB,
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
	
	public FindMistake(int id, String solution, String definition, String sentence, String optionA, String optionB,
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
		FindMistake other = (FindMistake) obj;
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
		if (optionA == null) {
			if (other.optionA != null)
				return false;
		} else if (!optionA.equals(other.optionA))
			return false;
		if (optionB == null) {
			if (other.optionB != null)
				return false;
		} else if (!optionB.equals(other.optionB))
			return false;
		if (optionC == null) {
			if (other.optionC != null)
				return false;
		} else if (!optionC.equals(other.optionC))
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
		return id + "\t" + solution + "\t\t" + definition + "\t\t"+ sentence + "\t\t"+ optionA + "\t\t" + optionB + "\t\t"+ optionC + "\t\t"
				+ score + "\t\t" + idCategory;
	}
	
}
