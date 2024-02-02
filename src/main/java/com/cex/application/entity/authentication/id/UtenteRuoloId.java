package com.cex.application.entity.authentication.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UtenteRuoloId  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_utente", nullable = false)
	private Long idUtente;
	
	@Column(name = "id_ruolo", nullable = false)
	private String idRuolo;

	public UtenteRuoloId() {
		super();
	}

	public UtenteRuoloId(Long idUtente, String idRuolo) {
		this();
		this.idUtente = idUtente;
		this.idRuolo = idRuolo;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(String idRuolo) {
		this.idRuolo = idRuolo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRuolo == null) ? 0 : idRuolo.hashCode());
		result = prime * result + ((idUtente == null) ? 0 : idUtente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtenteRuoloId other = (UtenteRuoloId) obj;
		if (idRuolo == null) {
			if (other.idRuolo != null)
				return false;
		} else if (!idRuolo.equals(other.idRuolo))
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (!idUtente.equals(other.idUtente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UtenteRuoloId [idUtente=" + idUtente + ", idRuolo=" + idRuolo + "]";
	}

}
