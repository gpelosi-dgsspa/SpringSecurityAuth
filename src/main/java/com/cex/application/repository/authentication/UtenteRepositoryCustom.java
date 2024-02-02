package com.cex.application.repository.authentication;

import java.util.Date;

import com.cex.application.entity.authentication.Utente;

public interface UtenteRepositoryCustom
{
	Utente changePasswordByUsername(String username, String password);
	Utente lockByUsername(String username);
	Utente unlockByUsername(String username);
	Utente disableByUsername(String username);
	Utente enableByUsername(String username);
	Utente expireByUsername(String username, Date expire);
}
