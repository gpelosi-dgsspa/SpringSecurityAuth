package com.cex.application.vo;

import java.util.Date;

public class UtenteExtendedVo 
{
	private Long id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Boolean locked;
	private Boolean enabled;
	private Date creationDate;
	private Date expirationDate;
	
	public UtenteExtendedVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	@Override
	public String toString() {
		return "UtenteExtendedVo [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome
				+ ", cognome=" + cognome + ", locked=" + locked + ", enabled=" + enabled + ", creationDate="
				+ creationDate + ", expirationDate=" + expirationDate + "]";
	}

}
