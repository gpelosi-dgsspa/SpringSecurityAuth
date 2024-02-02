package com.cex.application.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anagrafica_utente")
public class AnagraficaUtente 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "id_utente", nullable = false, unique = true)
	private Long idUtente;
	
	@Column(name = "nome", nullable = true)
	private String nome;
	
	@Column(name = "cognome", nullable = true)
	private String cognome;
	
	@Column(name = "data_nascita", nullable = true)
	private Date dataNascita;
	
	@Column(name = "codice_fiscale", nullable = true)
	private String codiceFiscale;
	
	@Column(name = "indirizzo", nullable = true)
	private String indirizzo;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "telefono", nullable = true)
	private String telefono;
	
	public AnagraficaUtente() {
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
		return "AnagraficaUtente [id=" + id + ", idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", codiceFiscale=" + codiceFiscale + ", indirizzo=" + indirizzo
				+ ", email=" + email + ", telefono=" + telefono + "]";
	}

}
