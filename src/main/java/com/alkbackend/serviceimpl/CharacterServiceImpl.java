package com.alkbackend.serviceimpl;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.alkbackend.entity.Character;

import com.alkbackend.repository.ICharacterRepository;
import com.alkbackend.service.ICharacterService;
import com.alkbackend.util.IImageFileImpl;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service

public class CharacterServiceImpl implements ICharacterService {

	@Autowired
	private ICharacterRepository repository;

	@Autowired
	private IImageFileImpl iiufi;

	@Override
	public void save(Character character) {

		this.repository.save(character);
	}



	
	public MappingJacksonValue findAll(Specification<Character> spec) {
				
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = 
				SimpleBeanPropertyFilter.serializeAllExcept("characters");
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("characterFilter", simpleBeanPropertyFilter)
				.addFilter("movieFilter", simpleBeanPropertyFilter);
		
		List<Character> characters = repository.findAll(spec);
		MappingJacksonValue mappingJacksonValue =  new MappingJacksonValue(characters);
		
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}



	@Override
	public void deleteById(Integer id) {

		Character character = this.repository.findById(id).get();

		iiufi.deleteFile(character.getImagen());
		repository.deleteById(id);

	}

	@Override
	public void saveW(Character character, MultipartFile imagen) throws IOException {
			
		iiufi.saveFile(imagen, character);

	
		this.repository.save(character);
	}

	



	@Override
	public void update(Character character, MultipartFile file) throws IOException {
		
		
		repository.save(character);
		
	}



	@Override
	public Character findById(Integer id) {
		Character character = this.repository.findById(id).get();
		return character;
	}


	public MappingJacksonValue getCharactersList() {
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = 
				SimpleBeanPropertyFilter.serializeAllExcept("history", "age", "movies",  "id");
		
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("characterFilter", simpleBeanPropertyFilter);
		
		List<Character> characters = repository.findAll();
		MappingJacksonValue mappingJacksonValue =  new MappingJacksonValue(characters);
		
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}

	


}