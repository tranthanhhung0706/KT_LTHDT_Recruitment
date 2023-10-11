package com.tttn.ptithcm.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/photo")
@Controller
public class PhotoController {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${file.upload.photo.path}")
	private String uploadPhotoPath;//Vị trí lưu trữ tập tin
	

	@RequestMapping(value="/view")
	@ResponseBody
	public ResponseEntity<?> viewPhoto(@RequestParam(name="filename",required=true)String filename){
		Resource resource = resourceLoader.getResource("file:" + uploadPhotoPath + filename); 
		try {
			return ResponseEntity.ok(resource);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
