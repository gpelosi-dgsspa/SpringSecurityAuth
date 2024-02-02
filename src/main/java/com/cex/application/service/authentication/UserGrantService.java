package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.UserGrantVo;

public interface UserGrantService 
{
	List<UserGrantVo> findByUsername(String username);
	List<String> findPagesByUsername(String username);
	List<UserGrantVo> getAll();
}
