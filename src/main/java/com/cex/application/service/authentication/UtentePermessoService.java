package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.UtentePermessoVo;

public interface UtentePermessoService 
{
	List<UtentePermessoVo> getAll();
	List<UtentePermessoVo> findByUserId(Long idUtente);
	UtentePermessoVo get(Long idUtente, String idPermesso);
	UtentePermessoVo add(UtentePermessoVo vo);
	UtentePermessoVo delete(Long idUtente, String idPermesso);
	List<UtentePermessoVo> deleteByUserId(Long idUtente);
}
