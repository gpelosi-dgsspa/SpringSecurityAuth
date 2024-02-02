package com.cex.application.entity.authentication.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RuoloPermessoId  implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_Ruolo", nullable = false)
	private String idRuolo;
	
	@Column(name = "id_permesso", nullable = false)
	private String idPermesso;

	public String getIdRuolo() {
		return idRuolo;
	}

	public RuoloPermessoId() {
		super();
	}

	public RuoloPermessoId(String idRuolo, String idPermesso) {
		this();
		this.idRuolo = idRuolo;
		this.idPermesso = idPermesso;
	}

	public void setIdRuolo(String idRuolo) {
		this.idRuolo = idRuolo;
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
		result = prime * result + ((idRuolo == null) ? 0 : idRuolo.hashCode());
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
		RuoloPermessoId other = (RuoloPermessoId) obj;
		if (idPermesso == null) {
			if (other.idPermesso != null)
				return false;
		} else if (!idPermesso.equals(other.idPermesso))
			return false;
		if (idRuolo == null) {
			if (other.idRuolo != null)
				return false;
		} else if (!idRuolo.equals(other.idRuolo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RuoloPermessoId [idRuolo=" + idRuolo + ", idPermesso=" + idPermesso + "]";
	}

	
}
