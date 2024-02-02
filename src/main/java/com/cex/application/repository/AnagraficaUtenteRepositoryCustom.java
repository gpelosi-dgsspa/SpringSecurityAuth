package com.cex.application.repository;

import java.util.List;

import com.cex.application.entity.AnagraficaUtente;

public interface AnagraficaUtenteRepositoryCustom 
{
	List<AnagraficaUtente> findByCriteria(AnagraficaUtente entity);
}
