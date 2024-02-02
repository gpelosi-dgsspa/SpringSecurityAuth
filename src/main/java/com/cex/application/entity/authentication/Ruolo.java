package com.cex.application.entity.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo 
{
	@Id
	@Column(name = "id", nullable = false)
	private String id;
	
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	
	public Ruolo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
