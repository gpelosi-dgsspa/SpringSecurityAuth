package com.cex.application.vo.authentication;

public class PermessoVo 
{
	private String id;
	private String descrizione;
	
	public PermessoVo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "PermessoVo [id=" + id + ", descrizione=" + descrizione + "]";
	}

}
