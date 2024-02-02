package com.cex.application.entity.authentication;

import com.cex.application.entity.authentication.id.RuoloPermessoId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruolo_permesso")
public class RuoloPermesso 
{
	@EmbeddedId
	private RuoloPermessoId id;
	
	public RuoloPermesso() {
		super();
	}

	public RuoloPermessoId getId() {
		return id;
	}

	public void setId(RuoloPermessoId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RuoloPermesso [id=" + id + "]";
	}

}
