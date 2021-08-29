package com.alkbackend.validator;

import org.springframework.stereotype.Service;

import com.alkbackend.entity.Character;
import com.alkbackend.exceptions.ApiUnprocessableEntity;

@Service
public interface ICharacterValidator  {

	boolean validator(Character character)throws ApiUnprocessableEntity;
}
