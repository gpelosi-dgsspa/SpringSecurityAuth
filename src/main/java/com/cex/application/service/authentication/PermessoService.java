package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.PermessoVo;

public interface PermessoService 
{
	List<PermessoVo> getAll();
	PermessoVo get(String id);
	PermessoVo add(PermessoVo vo);
	PermessoVo update(PermessoVo vo);
	PermessoVo delete(String id);
	boolean exists(String id);
}
