package com.alkbackend.entity;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "movie")
@JsonFilter("movieFilter")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native" )
	private int idMovie;

	@Column(length = 100)
	private String title;

	@Column
	private LocalDate creationDate;

	@Column
	private String imagen;

	@Column
	private float movieQualification = 0.f;
	

	  @ManyToMany
	  @JoinTable( name = "movie_characters",
			  joinColumns = @JoinColumn(name="fk_movie"), 
			  inverseJoinColumns = @JoinColumn(name ="fk_character"))
	  @JsonIgnoreProperties({"characters"})
	  private Set<Character> characters;
	  

	  
	  
	  public void addPersonage(Character character) {
		  characters.add(character);
	  }
	  
	  
    public Movie() {
	}

	public Movie(int idMovie, String title, LocalDate creationDate, String imagen, float movieQualification
			) {
		this.idMovie = idMovie;
		this.title = title;
		this.creationDate = creationDate;
		this.imagen = imagen;
		this.movieQualification = movieQualification;
		
		
	
	}

	
	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int id) {
		this.idMovie = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	

	public Set<Character> getCharacters() {
		return characters;
	}


	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}


	public float getMovieQualification() {
		return movieQualification;
	}



	public void setMovieQualification(float movieQualification) {
		this.movieQualification = movieQualification;
	}





	 
}
