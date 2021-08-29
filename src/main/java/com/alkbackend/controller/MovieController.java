package com.alkbackend.controller;

import java.time.LocalDate;
import java.util.HashMap;
//import java.util.HashMap;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkbackend.entity.Movie;


import com.alkbackend.service.IMovieService;
//import com.alkbackend.util.MovieQualificationUtil;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private IMovieService service;

	



	@GetMapping(value = "/list")
	public MappingJacksonValue findA() {
		MappingJacksonValue mjv = service.getMovies();
		return mjv;
	}

	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {

		String date = String.valueOf(movie.getCreationDate());
		LocalDate ld = LocalDate.parse(date);
		movie.setCreationDate(ld);

		this.service.save(movie);

		return new ResponseEntity<Map<String, Object>>(HttpStatus.CREATED);
	}

	@RequestMapping("/addpersonage/{characterId}/{movieId}")
	public ResponseEntity<?> addPersonage(@PathVariable(value = "characterId") Integer characterId,
			@PathVariable(value = "movieId") Integer movieId) {

		this.service.addCharacterToMovie(characterId, movieId);

		return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
	}

	@RequestMapping("movie_qualif/{idMovie}/{qualification}")
	public ResponseEntity<?> setQualif(@PathVariable(value = "idMovie") Integer idMovie,
			@PathVariable(value = "qualification") Integer qualification) {
	
		Map<String, Object> response = new HashMap<>();
		
		
	
		
		if(qualification <=0 || qualification >5) {
			response.put("Mensaje", "Solo se admiten valores del 1 al 5 para calificar la pelicula");
			return new ResponseEntity<Map<String, Object>>(response , HttpStatus.NOT_ACCEPTABLE);
		}else if(idMovie == null){
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		this.service.SetQualification(idMovie, qualification);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{idMovie}")
		public ResponseEntity<?> delete(@PathVariable("idMovie") Integer idMovie){
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			service.findByIdMovie(idMovie);
		}catch (Exception e) {
			response.put("Mensaje", "no existe una pelicula con el Id: " + idMovie);
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		service.deleteById(idMovie);
		 response.put("Mensaje", "Eliminado con éxito");
		return new ResponseEntity<>(response ,HttpStatus.OK);
	}

}
