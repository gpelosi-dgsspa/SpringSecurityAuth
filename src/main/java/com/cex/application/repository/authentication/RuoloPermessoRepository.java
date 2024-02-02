package com.cex.application.repository.authentication;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.authentication.RuoloPermesso;
import com.cex.application.entity.authentication.id.RuoloPermessoId;

public interface RuoloPermessoRepository extends JpaRepository<RuoloPermesso, RuoloPermessoId>
{
}
