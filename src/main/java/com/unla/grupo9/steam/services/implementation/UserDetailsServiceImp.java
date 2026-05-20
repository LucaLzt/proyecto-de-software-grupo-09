package com.unla.grupo9.steam.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.security.SecurityUser;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usuarioBuscado=userRepository.findByUsername(username);
		if(usuarioBuscado.isPresent()==true) {
			return new SecurityUser(usuarioBuscado.get());
		}else {
			throw new UsernameNotFoundException ("el usuario no existe");
		}
	}
	

	
	

}
