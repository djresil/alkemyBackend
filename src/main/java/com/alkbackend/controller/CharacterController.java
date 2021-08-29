package com.alkbackend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alkbackend.entity.Character;
import com.alkbackend.exceptions.ApiUnprocessableEntity;
import com.alkbackend.service.ICharacterService;
import com.alkbackend.validator.ICharacterValidator;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
	private ICharacterService service;

	@Autowired
	private ICharacterValidator characterValidator;
	
	

	@GetMapping(value = "/detailsbyid/{id}")
	public ResponseEntity<?> findById(@RequestParam(value = "id") Integer id) {
		Character character = null;
		Map<String, Object> response = new HashMap<>();

		try {
			character = service.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error interno del Servidor");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (character == null) {
			response.put("Mensaje", "El personaje: ".concat(id.toString().concat(" , no existe")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Character>(character, HttpStatus.OK);
	}
	
		

	@GetMapping
	public ResponseEntity<?> findAllByFilter(@And({ @Spec(path = "name", params = "name", spec = Like.class),
			@Spec(path = "age", params = "age", spec = Equal.class),
			@Spec(path = "idMovie", params = "idMovie", spec = Equal.class) }) Specification<Character> spec ) {
		
		MappingJacksonValue mjvspec = null;
		
		if(spec != null) {
		
	    mjvspec = service.findAll(spec);
		
		return ResponseEntity.ok(mjvspec);
				
			
		}
		MappingJacksonValue mjv = service.getCharactersList();
		return new ResponseEntity<>(mjv, HttpStatus.OK);
	}
		
	


	@PostMapping()
	public ResponseEntity<?> save(@ModelAttribute Character character, BindingResult result,
			@RequestParam(value = "imagen", required = false) MultipartFile imagen)
			throws ApiUnprocessableEntity, IOException {
		Map<String, Object> response = new HashMap<>();
		
	

		this.characterValidator.validator(character);
		try {
			if (!imagen.isEmpty()) {
				this.service.saveW(character, imagen);

			} else {

				this.service.save(character);
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") Integer id,
			@RequestParam(value = "imagen", required = false) MultipartFile imagen, @ModelAttribute Character character,
			BindingResult result) throws ApiUnprocessableEntity, IOException {
		
		Character characterDB = service.findById(character.getId());
		
		if(imagen.getOriginalFilename() !=  characterDB.getName()) {
			if(this.characterValidator.validator(character)) {
			this.service.saveW(character, imagen);
			
			return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return  new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} 
		if(this.characterValidator.validator(character)){}
		service.save(character);
		return ResponseEntity.ok(character);
		}
		
	


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deletedById(@PathVariable("id") Integer id) {

		Map<String, Object> response = new HashMap<>();
		
		this.service.deleteById(id);
		response.put("Mensaje", "Borrado con éxito");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
