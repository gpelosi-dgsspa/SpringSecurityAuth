package com.cex.application.repository.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.UserGrant;

public interface UserGrantRepository extends JpaRepository<UserGrant, Long>
{
	List<UserGrant> findByUsername(String username);
}
