package com.seeker.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seeker.dao.UserDao;
import com.seeker.models.UserEntity;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

	private AuthenticationManager authenticationManager;
	
	private UserDao userDao;
	
	private PasswordEncoder passwordEncoder;


	public void doauthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
 
		try {
			authenticationManager.authenticate(authToken);
		} catch (BadCredentialsException ex) {
			throw new BadCredentialsException("Invalid email or password");
		}
	}
	
	 public String createUser(UserEntity user) {
		 
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);

	        userDao.save(user);
	        return "User Created Successfully";
	    }
}
