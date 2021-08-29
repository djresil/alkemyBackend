package com.alkbackend.validator;

import org.springframework.stereotype.Component;

import com.alkbackend.entity.Character;
import com.alkbackend.exceptions.ApiUnprocessableEntity;

@Component
public class CharacterValidatorImpl implements ICharacterValidator {

	@Override
	public boolean validator(Character character) throws ApiUnprocessableEntity {
	
		String ph = character.getHistory();
		
		
		
		if(ph.isEmpty() || ph == null) {
			message("Debe completar la historia del personaje");
			return false;
		}
		if(ph.length() > 500 ) {
			message("La historia no puede exceder de 500 caracteres");
			return false;
		}
		if(character.getAge() <= 0  ) {
			message("La edad no es valida" );
			return false;
		}
		return true;
	
	}
	
	private void message(String message) throws ApiUnprocessableEntity {
		
		throw new ApiUnprocessableEntity(message);
		
	}

}
