package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.UtentePermesso;
import com.cex.application.entity.authentication.id.UtentePermessoId;

public interface UtentePermessoRepository extends JpaRepository<UtentePermesso, UtentePermessoId>
{
}
