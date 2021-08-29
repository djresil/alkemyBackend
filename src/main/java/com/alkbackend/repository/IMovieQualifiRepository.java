package com.alkbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkbackend.entity.MovieQualif;

@Repository
public interface IMovieQualifiRepository extends JpaRepository<MovieQualif, Integer >{

	List<MovieQualif> findAllByIdMovie(Integer idMovie);
	
	@Modifying
	@Query(value= "DELETE FROM MovieQualif mq where mq.idMovie =:idmovie")
    int deleteByIdMovie(@Param("idmovie") Integer idMovie);
	
	
	
	
	}
