package it.contrader.dto;

public class GuessPictureDTO {
	
	private int id;
	private Integer idCategory;
	private Integer score;
	private String solution;
	private String image;
	
	public GuessPictureDTO() {}
	
	public GuessPictureDTO(int id, Integer idCategory, Integer score, String solution, String image) {
		this.id = id;
		this.idCategory = idCategory;
		this.score = score;
		this.solution = solution;
		this.image = image;
	}

	public GuessPictureDTO(Integer idCategory, Integer score, String solution, String image) {
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
		return id + "\t" + solution + "\t\t" + image + "\t\t" + score + "\t\t" + idCategory;
	}
	
}
