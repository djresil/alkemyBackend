package com.alkbackend.service;


import org.springframework.http.converter.json.MappingJacksonValue;

import com.alkbackend.entity.Movie;

public interface IMovieService {

	MappingJacksonValue getMovies();

	void save(Movie movie);

	void addCharacterToMovie(Integer characterId, Integer movieId);

	void SetQualification(Integer IdMovie, Integer qualifInteger);

	Movie findByIdMovie(Integer id);

	void deleteById(Integer id);
	
	
}
