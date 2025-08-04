package com.seeker;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

@SpringBootApplication // includes @Configuration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * Configure ModelMapper as spring bean - so thar SC will manage it's life cycle
	 * + provide it as the depcy
	 */
	@Bean // method level annotation - to tell SC , following method
	// rets an object - which has to be managed as a spring bean
	// manages - life cycle +
	public ModelMapper modelMapper() {
		System.out.println("in model mapper creation");
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				/*
				 * To tell ModelMapper to map only those props whose names match in src n dest.
				 * objects
				 */
				.setMatchingStrategy(MatchingStrategies.STRICT)
				/*
				 * To tell ModelMapper not to transfer nulls from src -> dest
				 */
				.setPropertyCondition(Conditions.isNotNull());// use case - PUT
		return mapper;

	}
	
	
	@Bean
	 Cloudinary getCloudinary() {
		
		Map config = new HashMap();
		
		config.put("cloud_name", "dmjqbvrtd");
		config.put("api_key", "559336967177573");
		config.put("api_secret", "6sZjTPlRX4Uhy2gZeRBsr_meTrQ");
		config.put("secure", true);
		
		return new Cloudinary(config);
	}

}
