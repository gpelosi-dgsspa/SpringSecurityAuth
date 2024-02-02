package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.RuoloPermessoVo;

public interface RuoloPermessoService 
{
	List<RuoloPermessoVo> getAll();
	RuoloPermessoVo get(String idRuolo, String idPermesso);
	RuoloPermessoVo add(RuoloPermessoVo vo);
	RuoloPermessoVo delete(String idRuolo, String idPermesso);
	List<RuoloPermessoVo> findByRuolo(String idRuolo);
	List<RuoloPermessoVo> deleteByRuolo(String idRuolo);
	List<RuoloPermessoVo> deleteAndAddMultiple(String idRuolo, List<RuoloPermessoVo> lista);
}
