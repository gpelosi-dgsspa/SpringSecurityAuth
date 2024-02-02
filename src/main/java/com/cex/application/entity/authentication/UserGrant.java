package com.cex.application.entity.authentication;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_permessi_utente")
public class UserGrant 
{
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "id_utente", nullable = false)
	private Long idUtente;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "locked", nullable = false)
	private Boolean locked;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@Column(name = "created", nullable = true)
	private Date creationDate;
	
	@Column(name = "expire", nullable = true)
	private Date expirationDate;
	
	@Column(name = "id_permesso", nullable = false)
	private String idPermesso;
	
	@Column(name = "descrizione", nullable = true)
	private String descrizione;
	
	public UserGrant() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getIdPermesso() {
		return idPermesso;
	}

	public void setIdPermesso(String idPermesso) {
		this.idPermesso = idPermesso;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "UserGrant [id=" + id + ", idUtente=" + idUtente + ", username=" + username + ", locked=" + locked
				+ ", enabled=" + enabled + ", creationDate=" + creationDate + ", expirationDate=" + expirationDate
				+ ", idPermesso=" + idPermesso + ", descrizione=" + descrizione + "]";
	}

}
