package com.cex.application.vo;

import java.util.Date;

public class AnagraficaUtenteVo 
{
	private Long id;
	private Long idUtente;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String codiceFiscale;
	private String indirizzo;
	private String email;
	private String telefono;

	public AnagraficaUtenteVo() {
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

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "AnagraficaUtenteVo [id=" + id + ", idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", codiceFiscale=" + codiceFiscale + ", indirizzo=" + indirizzo
				+ ", email=" + email + ", telefono=" + telefono + "]";
	}
	
}
