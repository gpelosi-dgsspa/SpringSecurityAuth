package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, String>
{
}
