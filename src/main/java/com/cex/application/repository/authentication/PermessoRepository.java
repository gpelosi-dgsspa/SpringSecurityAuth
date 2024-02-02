package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.Permesso;

public interface PermessoRepository extends JpaRepository<Permesso, String>
{
}
