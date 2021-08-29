package com.alkbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkbackend.entity.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Integer>{
	
	Movie findByTitle(String title);
	
	
	
	
	
	
	
}
