package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.RuoloVo;

public interface RuoloService 
{
	List<RuoloVo> getAll();
	RuoloVo get(String id);
	RuoloVo add(RuoloVo vo);
	RuoloVo update(RuoloVo vo);
	RuoloVo delete(String id);
}
