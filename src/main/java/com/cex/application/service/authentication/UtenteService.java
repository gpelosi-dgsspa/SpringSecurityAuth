package com.cex.application.service.authentication;

import java.util.Date;
import java.util.List;

import com.cex.application.vo.UtenteExtendedVo;
import com.cex.application.vo.authentication.UtenteVo;

public interface UtenteService 
{
	List<UtenteVo> getAll();
	UtenteVo get(Long id);
	UtenteExtendedVo getExtended(Long id);
	UtenteVo add(UtenteVo vo);
	UtenteVo register(UtenteVo vo);
	UtenteVo update(UtenteVo vo);
	UtenteVo delete(Long id);
	UtenteVo deleteCascade(Long id);
	UtenteVo findByUsername(String username);
	UtenteExtendedVo findExtendedByUsername(String username);
	List<UtenteVo> findByExample(UtenteVo vo);
	UtenteVo deleteByUsername(String username);
	String resetPasswordByUsername(String username);
	UtenteVo changePasswordByUsername(String username, String password);
	UtenteVo lockByUsername(String username);
	UtenteVo unlockByUsername(String username);
	UtenteVo disableByUsername(String username);
	UtenteVo enableByUsername(String username);
	UtenteVo expireByUsername(String username, Date expire);
	String getCurrent();
	UtenteVo getFullCurrent();
	UtenteExtendedVo getFullExtendedCurrent();
	boolean hasHierarchyAuthority(String auth);
	List<String> getAllHierarchyAuthorities();
	List<String> getHierarchyAuthorities();
	List<String> getHierarchyRoles();
}
