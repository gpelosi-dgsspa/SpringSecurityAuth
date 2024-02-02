package com.cex.application.repository.authentication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.Gerarchia;

public interface GerarchiaRepository  extends JpaRepository<Gerarchia, Integer>
{
	List<Gerarchia> findByParentAuth(String parentAuth);
	List<Gerarchia> findByAuthorityType(String authorityType);
}
