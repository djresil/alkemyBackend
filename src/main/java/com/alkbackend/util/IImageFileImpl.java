package com.alkbackend.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.alkbackend.entity.Character;

public interface IImageFileImpl {

	void saveFile(MultipartFile file, Character character) throws IOException;

	void convertMultiToFile(MultipartFile file, Character character);

	void deleteFile(String FileName);

}
