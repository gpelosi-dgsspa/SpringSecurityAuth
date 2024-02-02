package com.cex.application.vo.authentication;

public class RuoloPermessoVo 
{
	private String idRuolo;
	private String idPermesso;
	
	public RuoloPermessoVo() {
		super();
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
		return "RuoloPermessoVo [idRuolo=" + idRuolo + ", idPermesso=" + idPermesso + "]";
	}

}
