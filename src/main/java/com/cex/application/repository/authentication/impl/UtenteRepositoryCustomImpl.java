package com.cex.application.repository.authentication.impl;

import java.util.Date;
import java.util.List;

import com.cex.application.entity.authentication.Utente;
import com.cex.application.repository.authentication.UtenteRepositoryCustom;
import com.cex.application.repository.common.EntityDao;

public class UtenteRepositoryCustomImpl extends EntityDao<Utente> implements UtenteRepositoryCustom
{
	@Override
	public Utente changePasswordByUsername(String username, String password) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setPassword(password);
			entity = super.update(entity);
		}
		return entity;
	}
	
	@Override
	public Utente lockByUsername(String username) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setLocked(Boolean.valueOf(true));
			entity = super.update(entity);
		}
		return entity;
	}
	
	@Override
	public Utente unlockByUsername(String username) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setLocked(Boolean.valueOf(false));
			entity = super.update(entity);
		}
		return entity;
	}
	
	@Override
	public Utente disableByUsername(String username) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setEnabled(Boolean.valueOf(false));
			entity = super.update(entity);
		}
		return entity;
	}
	
	@Override
	public Utente enableByUsername(String username) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setEnabled(Boolean.valueOf(true));
			entity = super.update(entity);
		}
		return entity;
	}

	@Override
	public Utente expireByUsername(String username, Date expire) {
		List<Utente> lista = super.findByProperty("username", username);
		Utente entity = null;
		if(lista!=null && !lista.isEmpty()) {
			entity = lista.get(0);
			entity.setExpirationDate(expire);
			entity = super.update(entity);
		}
		return entity;
	}
}
