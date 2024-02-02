package com.cex.application.vo.authentication;

public class UtenteRuoloVo 
{
	private Long idUtente;
	private String idRuolo;
	
	public UtenteRuoloVo() {
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

	@Override
	public String toString() {
		return "UtenteRuoloVo [idUtente=" + idUtente + ", idRuolo=" + idRuolo + "]";
	}

}
