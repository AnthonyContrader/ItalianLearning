package it.contrader.model;

public class OrganizeSentence {
	
	private int id;
	private String solution;
	private String sentence;
	private Integer score;
	private String definition;
	private Integer idCategory;
	private Integer idLevel;
	
	
	public OrganizeSentence() {
		
	}

	
	public OrganizeSentence(int id, String solution, String sentence, Integer score, String definition,
			Integer idCategory, Integer idLevel) {
		super();
		this.id = id;
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}

	public OrganizeSentence(String solution, String sentence, Integer score, String definition, Integer idCategory,
			Integer idLevel) {
		super();
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
		this.idLevel = idLevel;
	}



	public OrganizeSentence(int id, String solution, String sentence, Integer score, String definition,
			Integer idCategory) {
		super();
		this.id = id;
		this.solution = solution;
		this.sentence = sentence;
		this.score = score;
		this.definition = definition;
		this.idCategory = idCategory;
	}


	public OrganizeSentence(String solution, String sentence, Integer score, String definition, Integer idCategory) {
		super();
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


	public Integer getIdLevel() {
		return idLevel;
	}


	public void setIdLevel(Integer idLevel) {
		this.idLevel = idLevel;
	}


	@Override
	public String toString() {
		return "OrganizeSentence [id=" + id + ", solution=" + solution + ", sentence=" + sentence + ", score=" + score
				+ ", definition=" + definition + ", idCategory=" + idCategory + ", idLevel=" + idLevel + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrganizeSentence other = (OrganizeSentence) obj;
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
