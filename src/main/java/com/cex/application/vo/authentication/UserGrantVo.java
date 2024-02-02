package com.cex.application.vo.authentication;

import java.util.Date;

public class UserGrantVo 
{
	private Long id;
	private Long idUtente;
	private String username;
	private Boolean locked;
	private Boolean enabled;
	private Date creationDate;
	private Date expirationDate;
	private String idPermesso;
	private String descrizione;
	
	public UserGrantVo() {
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
		return "UserGrantVo [id=" + id + ", idUtente=" + idUtente + ", username=" + username + ", locked=" + locked
				+ ", enabled=" + enabled + ", creationDate=" + creationDate + ", expirationDate=" + expirationDate
				+ ", idPermesso=" + idPermesso + ", descrizione=" + descrizione + "]";
	}

	
}
