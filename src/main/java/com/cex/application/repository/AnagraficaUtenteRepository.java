package com.cex.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cex.application.entity.AnagraficaUtente;

public interface AnagraficaUtenteRepository extends JpaRepository<AnagraficaUtente, Long>, AnagraficaUtenteRepositoryCustom
{
	AnagraficaUtente findByIdUtente(Long idUtente);
	List<AnagraficaUtente> findByIndirizzoLikeIgnoreCase(String indirizzo);
	List<AnagraficaUtente> findByCodiceFiscale(String codiceFiscale);
	List<AnagraficaUtente> findByCriteria(AnagraficaUtente anagrafica);
}
