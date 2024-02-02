package com.cex.application.service;

import java.util.List;

import com.cex.application.vo.AnagraficaUtenteVo;

public interface AnagraficaUtenteService 
{
	List<AnagraficaUtenteVo> getAll();
	AnagraficaUtenteVo get(Long id);
	AnagraficaUtenteVo add(AnagraficaUtenteVo vo);
	AnagraficaUtenteVo update(AnagraficaUtenteVo vo);
	AnagraficaUtenteVo saveOrUpdate(AnagraficaUtenteVo vo);
	AnagraficaUtenteVo delete(Long id);
	AnagraficaUtenteVo findByIdUtente(Long idUtente);
	List<AnagraficaUtenteVo> findByIndirizzo(String indirizzo);
	List<AnagraficaUtenteVo> findByCodiceFiscale(String cf);
	List<AnagraficaUtenteVo> findByCriteria(AnagraficaUtenteVo vo);
	List<AnagraficaUtenteVo> findByExample(AnagraficaUtenteVo vo);
}
