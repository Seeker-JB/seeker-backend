package com.seeker.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

	private Cloudinary cloudinary;

	@Override
	public String upload(MultipartFile file) {
		 try {
				Map<String, Object> data = cloudinary.uploader().upload(file.getBytes(), Map.of());
		        return (String) data.get("secure_url");
		    } catch (IOException e) {
		    	
		        throw new RuntimeException("Image upload failed", e);
		    }
	}
	
	
	
}


