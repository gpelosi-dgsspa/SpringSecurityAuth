package com.cex.application.vo.authentication;

public class UserGrantWebVo 
{
	private Long idUtente;
	private String idRuolo;
	private String idPermesso;
	
	public UserGrantWebVo() {
		super();
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

	public String getIdPermesso() {
		return idPermesso;
	}

	public void setIdPermesso(String idPermesso) {
		this.idPermesso = idPermesso;
	}

	@Override
	public String toString() {
		return "UserGrantWebVo [idUtente=" + idUtente + ", idRuolo=" + idRuolo + ", idPermesso=" + idPermesso + "]";
	}
}
