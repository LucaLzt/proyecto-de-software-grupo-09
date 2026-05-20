package com.unla.grupo9.steam.security;

import org.springframework.security.core.GrantedAuthority;

import com.unla.grupo9.steam.entities.UserRole;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityRol implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserRole userRole;

	@Override
	public String getAuthority() {
		return userRole.getRole().toString();
	}
	
	

}
