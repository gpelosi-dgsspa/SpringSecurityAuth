package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long>, UtenteRepositoryCustom
{
	Utente findByUsername(String username);
}
