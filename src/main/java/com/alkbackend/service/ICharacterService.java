package com.alkbackend.service;

import java.io.IOException;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.multipart.MultipartFile;

import com.alkbackend.entity.Character;

public interface ICharacterService {

	public Character findById(Integer id);

	void save(Character character);

	public MappingJacksonValue findAll(Specification<Character> spec);

	public void update(Character character, MultipartFile file) throws IOException;

	void deleteById(Integer id);

	void saveW(Character character, MultipartFile imagen) throws IOException;
	
	public MappingJacksonValue getCharactersList();
}
