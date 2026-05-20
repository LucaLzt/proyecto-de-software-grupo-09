package com.unla.grupo9.steam.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.services.IUserService;

@Service
public class UserService implements IUserService {

@Autowired	
private  IUserRepository userRepository;

@Autowired
private BCryptPasswordEncoder passwordEncoder;
@Override
public User saveOrUpdate(User user) {
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	return userRepository.save(user);
	}
	
}