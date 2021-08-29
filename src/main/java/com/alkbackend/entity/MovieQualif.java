package com.alkbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class MovieQualif {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Integer qualification;

	@Column
	private Integer numberOfVote;

	@Column
	private Integer idMovie;

	

	public MovieQualif() {
	}
	
	

	public MovieQualif(int id, Integer qualification, Integer numberOfVote, Integer idMovie) {
		this.id = id;
		this.qualification = qualification;
		this.numberOfVote = numberOfVote;
		this.idMovie = idMovie;
	
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Integer getIdMovie() {
		return idMovie;
	}



	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}



	public int getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	public int getNumberOfVote() {
		return numberOfVote;
	}

	public void setNumberOfVote(Integer numberOfVote) {
		this.numberOfVote = numberOfVote;
	}



	@Override
	public String toString() {
		return "MovieQualif [id=" + id + ", qualification=" + qualification + ", numberOfVote=" + numberOfVote
				+ ", idMovie=" + idMovie + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
		result = prime * result + ((numberOfVote == null) ? 0 : numberOfVote.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
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
		MovieQualif other = (MovieQualif) obj;
		if (id != other.id)
			return false;
		if (idMovie == null) {
			if (other.idMovie != null)
				return false;
		} else if (!idMovie.equals(other.idMovie))
			return false;
		if (numberOfVote == null) {
			if (other.numberOfVote != null)
				return false;
		} else if (!numberOfVote.equals(other.numberOfVote))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		return true;
	}

	
}
