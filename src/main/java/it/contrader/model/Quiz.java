package it.contrader.model;

public class Quiz {

	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String definition;
	
	public Quiz () {
	}	
	public Quiz (String solution, String definition) {
		this.solution = solution;
		this.definition = definition;
}
	
	public Quiz (String solution, String definition, int id,  Integer idCategory,  Integer score) {
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
	}
	
	public int getId() {
		return id;
	}
	public Integer getIdCategory() {
		return idCategory;
	}
	public Integer getScore() {
		return score;
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
	
	public void setDefinition (String definition) {
		this.definition = definition;
	}
	public void setScore (Integer score) {
		this.score = score;
	}
	public void setIdCategory (Integer idCategory) {
		this.idCategory = idCategory;
	
	}
	
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", idCategory=" + idCategory + ", score=" + score + ", solution=" + solution
				+ ", definition=" + definition + "]";
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
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		return true;
	}
}