package com.cex.application.vo.authentication;

public class GerarchiaVo 
{
	private Integer id;
	private String parentAuth;
	private String childAuth;
	private String authorityType;
	
	public GerarchiaVo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChildAuth() {
		return childAuth;
	}

	public void setChildAuth(String childAuth) {
		this.childAuth = childAuth;
	}

	public String getParentAuth() {
		return parentAuth;
	}

	public void setParentAuth(String parentAuth) {
		this.parentAuth = parentAuth;
	}

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	@Override
	public String toString() {
		return "GerarchiaVo [id=" + id + ", parentAuth=" + parentAuth + ", childAuth=" + childAuth + ", authorityType="
				+ authorityType + "]";
	}

}
