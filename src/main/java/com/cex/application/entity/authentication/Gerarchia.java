package com.cex.application.entity.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gerarchia")
public class Gerarchia 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "child_auth", nullable = false)
	private String childAuth;
	
	@Column(name = "parent_auth", nullable = false)
	private String parentAuth;
	
	@Column(name = "authority_type", nullable = false)
	private String authorityType;
	
	public Gerarchia() {
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
		return "Gerarchia [id=" + id + ", childAuth=" + childAuth + ", parentAuth=" + parentAuth + ", authorityType="
				+ authorityType + "]";
	}

}
