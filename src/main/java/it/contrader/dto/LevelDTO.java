package it.contrader.dto;

public class LevelDTO {
private int id;
private int score;
private String name;
private String description;

public LevelDTO() {
	
}

public LevelDTO(int id, int score, String name, String description) {
	this.id = id;
	this.score = score;
	this.name = name;
	this.description = description;
}

public LevelDTO(int score, String name, String description) {
	this.score = score;
	this.name = name;
	this.description = description;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

@Override
public String toString() {
	return "LevelDTO [id=" + id + ", score=" + score + ", name=" + name + ", description=" + description + "]";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LevelDTO other = (LevelDTO) obj;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (id != other.id)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (score != other.score)
		return false;
	return true;
}


}
