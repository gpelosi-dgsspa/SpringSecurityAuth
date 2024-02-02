package com.cex.application.entity.authentication;

import com.cex.application.entity.authentication.id.UtenteRuoloId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente_ruolo")
public class UtenteRuolo 
{
	@EmbeddedId
	private UtenteRuoloId id;
	
	public UtenteRuolo() {
		super();
	}

	public UtenteRuoloId getId() {
		return id;
	}

	public void setId(UtenteRuoloId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UtenteRuolo [id=" + id + "]";
	}

}
