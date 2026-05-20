package com.unla.grupo9.steam.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.unla.grupo9.steam.utils.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="role", nullable=false)
	private Roles role;

	@Column(name="createdat")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name="updatedat")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public UserRole(Roles role, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserRole(Roles role) {
		super();
		this.role = role;
	}
}