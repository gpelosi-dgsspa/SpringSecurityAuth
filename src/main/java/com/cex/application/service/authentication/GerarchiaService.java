package com.cex.application.service.authentication;

import java.util.List;

import com.cex.application.vo.authentication.GerarchiaVo;

public interface GerarchiaService 
{
	List<GerarchiaVo> getAll();
	List<GerarchiaVo> findByParentAuth(String parentAuth);
	List<GerarchiaVo> findByAuthorityType(String authorityType);
	List<String> getDbGerarchieApplicative();
	String getHierarchySource();
	GerarchiaVo get(Integer id);
	GerarchiaVo add(GerarchiaVo vo);
	GerarchiaVo update(GerarchiaVo vo);
	GerarchiaVo delete(Integer id);
}
