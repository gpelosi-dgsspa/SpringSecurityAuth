package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.UtenteRuoloVo;

public interface UtenteRuoloService 
{
	List<UtenteRuoloVo> getAll();
	List<UtenteRuoloVo> findByUserId(Long idUtente);
	UtenteRuoloVo get(Long idUtente, String idRuolo);
	UtenteRuoloVo add(UtenteRuoloVo vo);
	UtenteRuoloVo delete(Long idUtente, String idRuolo);
	List<UtenteRuoloVo> deleteByUserId(Long idUtente);
}
