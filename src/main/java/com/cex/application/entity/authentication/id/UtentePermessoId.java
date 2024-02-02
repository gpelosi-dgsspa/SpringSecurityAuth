package com.cex.application.entity.authentication.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UtentePermessoId  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_utente", nullable = false)
	private Long idUtente;
	
	@Column(name = "id_permesso", nullable = false)
	private String idPermesso;

	public Long getIdUtente() {
		return idUtente;
	}

	public UtentePermessoId() {
		super();
	}

	public UtentePermessoId(Long idUtente, String idPermesso) {
		this();
		this.idUtente = idUtente;
		this.idPermesso = idPermesso;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}


	public String getIdPermesso() {
		return idPermesso;
	}

	public void setIdPermesso(String idPermesso) {
		this.idPermesso = idPermesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPermesso == null) ? 0 : idPermesso.hashCode());
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
		UtentePermessoId other = (UtentePermessoId) obj;
		if (idPermesso == null) {
			if (other.idPermesso != null)
				return false;
		} else if (!idPermesso.equals(other.idPermesso))
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
		return "UtentePermessoId [idUtente=" + idUtente + ", idPermesso=" + idPermesso + "]";
	}

	
}
