package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.UserInfoVo;

public interface LoginService 
{
	List<String> getLoggedUsers();
	boolean forceLogout();
	boolean forceAllLogout();
	boolean forceUserLogout(String username);
	boolean forceMultiUserLogout(List<String> listaUtenti);
	List<UserInfoVo> getLoggedUsersInfo();
}
