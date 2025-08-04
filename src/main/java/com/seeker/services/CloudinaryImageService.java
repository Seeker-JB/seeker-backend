package com.seeker.services;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {
	
	public String upload(MultipartFile file);

}
