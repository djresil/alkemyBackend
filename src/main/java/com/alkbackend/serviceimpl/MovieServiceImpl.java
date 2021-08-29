package com.alkbackend.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.alkbackend.entity.Movie;
import com.alkbackend.entity.MovieQualif;
import com.alkbackend.entity.Character;
import com.alkbackend.repository.IMovieRepository;
import com.alkbackend.repository.ICharacterRepository;
import com.alkbackend.repository.IMovieQualifiRepository;
import com.alkbackend.service.IMovieService;
import com.alkbackend.util.MovieQualificationUtil;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository repo;

	@Autowired
	private ICharacterRepository repopers;

	@Autowired
	private IMovieQualifiRepository imqr;

	private Character character;

	private Movie movie;

	private MovieQualif mq;

	public MappingJacksonValue getMovies() {
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = 
				SimpleBeanPropertyFilter.serializeAllExcept("id", "movieQualification", "characters");
		
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("movieFilter", simpleBeanPropertyFilter);
		
		List<Movie> movie = repo.findAll();
		MappingJacksonValue mappingJacksonValue =  new MappingJacksonValue(movie);
		
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}

	@Override
	@Transactional
	public void save(Movie movie) {
		

		this.repo.save(movie);
		
		Movie mov = repo.findByTitle(movie.getTitle());

		int idM = mov.getIdMovie();
			
		for (int i = 1; i <= 5; i++) {
			mq = new MovieQualif(0, i, 0, idM);

			imqr.save(mq);
		  }
		}

		
	

	public void SetQualification(Integer idMovie, Integer qualifInteger) {

		MovieQualificationUtil mu = new MovieQualificationUtil();
		 
		List<MovieQualif> list = imqr.findAllByIdMovie(idMovie);
		

		for (MovieQualif mvqf : list) {
			if (mvqf.getQualification() == qualifInteger) {

				int qu = mvqf.getNumberOfVote() + 1;

				mvqf.setNumberOfVote(qu);
 
				Movie mov = repo.findById(idMovie).get();

				Map<Integer, Integer> ls = new HashMap<Integer, Integer>();
				ls = mu.utilListToMap(list);

				float co = mu.promedio(ls);

				mov.setMovieQualification(co);

				repo.save(mov);

				imqr.save(mvqf);
			}
		}

	}

	@Override
	public void addCharacterToMovie(Integer characterId, Integer movieId) {
		character = this.repopers.findById(characterId).get();
		movie = this.repo.getById(movieId);
		movie.addPersonage(character);

		this.repo.save(movie);

	}

	@Override
	public Movie findByIdMovie(Integer id) {
		
		Movie mov = this.repo.findById(id).get();
		
		return mov;
		
	}
	

	
	@Override
	@Transactional
	public void deleteById(Integer idMovie) {
		
		repo.deleteById(idMovie);
		
		
		
		imqr.deleteByIdMovie(idMovie);
		
		
	}
	

}
