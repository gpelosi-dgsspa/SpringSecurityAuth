package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.UtenteRuolo;
import com.cex.application.entity.authentication.id.UtenteRuoloId;

public interface UtenteRuoloRepository extends JpaRepository<UtenteRuolo, UtenteRuoloId>
{
}
