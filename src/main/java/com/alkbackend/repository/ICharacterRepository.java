package com.alkbackend.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.alkbackend.entity.Character;

public interface ICharacterRepository extends JpaRepository<Character, Integer>, JpaSpecificationExecutor<Character>{

	
	
	

	
}
