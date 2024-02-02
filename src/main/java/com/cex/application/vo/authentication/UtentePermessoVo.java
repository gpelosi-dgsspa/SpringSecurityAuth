package com.cex.application.vo.authentication;

public class UtentePermessoVo 
{
	private Long idUtente;
	private String idPermesso;
	
	public UtentePermessoVo() {
		super();
	}

	public Long getIdUtente() {
		return idUtente;
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
	public String toString() {
		return "UtentePermessoVo [idUtente=" + idUtente + ", idPermesso=" + idPermesso + "]";
	}

}
