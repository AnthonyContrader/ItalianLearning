package it.contrader.model;

public class GuessPicture {
	
	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String image;
	

	public GuessPicture() {}

	public GuessPicture(int id, Integer idCategory, Integer score, String solution, String image) {
		super();
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
	}

	
	public GuessPicture(Integer idCategory, Integer score, String solution, String image) {
		super();
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "GuessPicture [id=" + id + ", idCategory=" + idCategory + ", score=" + score + ", solution=" + solution
				+ ", image=" + image + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuessPicture other = (GuessPicture) obj;
		if (id != other.id)
			return false;
		if (idCategory == null) {
			if (other.idCategory != null)
				return false;
		} else if (!idCategory.equals(other.idCategory))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
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
