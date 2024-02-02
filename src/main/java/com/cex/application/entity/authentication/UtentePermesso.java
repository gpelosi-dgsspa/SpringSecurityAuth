package com.cex.application.entity.authentication;

import com.cex.application.entity.authentication.id.UtentePermessoId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente_permesso")
public class UtentePermesso 
{
	@EmbeddedId
	private UtentePermessoId id;
	
	public UtentePermesso() {
		super();
	}

	public UtentePermessoId getId() {
		return id;
	}

	public void setId(UtentePermessoId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UtentePermesso [id=" + id + "]";
	}

}
