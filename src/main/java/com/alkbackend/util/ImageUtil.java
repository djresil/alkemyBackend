package com.alkbackend.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alkbackend.entity.Character;


@Component
public class ImageUtil implements IImageFileImpl {

		private String  src = ".//src//main//resources//images";
		
	
		public void saveFile(MultipartFile file, Character character) throws IOException {
			if(!file.isEmpty()) {
				String nameMod = FileNameUtil.getFileName(file.getOriginalFilename());
				byte[] bytes = file.getBytes();
				character.setImagen(nameMod);
				Path path = Paths.get( src+ "//" + character.getImagen());
				
				Files.write(path, bytes);
				
			}
			
		}

		@Override
		public void convertMultiToFile(MultipartFile file, Character character) {
			Path path = Paths.get( src+ "//" + character.getImagen());
		try {
			OutputStream os =  Files.newOutputStream(path);
			os.write(file.getBytes());
		} catch (Exception e) {
			System.out.println("todo se derrumbo dentro de mi, dentro de mi");
		}
		
		
			
		}


	
		public void deleteFile( String fileName) {
			
		File delFile = new File (src + "//"+ fileName);
			if(delFile.isFile() && delFile.exists()) {
				delFile.delete();
			}
		}		



	

}
