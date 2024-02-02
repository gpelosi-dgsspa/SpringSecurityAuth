package com.cex.application.service.authentication.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.Gerarchia;
import com.cex.application.repository.authentication.GerarchiaRepository;
import com.cex.application.service.authentication.GerarchiaService;
import com.cex.application.util.AuthenticationUtil;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.GerarchiaVo;

@Service
public class GerarchiaServiceImpl implements GerarchiaService
{
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GerarchiaRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public List<GerarchiaVo> getAll() {
		List<Gerarchia> lista = repo.findAll();
		List<GerarchiaVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, GerarchiaVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public List<GerarchiaVo> findByParentAuth(String parentAuth) {
		List<Gerarchia> lista = repo.findByParentAuth(parentAuth);
		List<GerarchiaVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, GerarchiaVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	public List<GerarchiaVo> findByAuthorityType(String authorityType) {
		List<Gerarchia> lista = repo.findByAuthorityType(authorityType);
		List<GerarchiaVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, GerarchiaVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	public List<String> getDbGerarchieApplicative() {
		String[] gerarchie = AuthenticationUtil.readDBHierarchyList(repo);
		return Arrays.asList(gerarchie);
	}
	
	public String getHierarchySource() {
		return AuthenticationUtil.getHierarchySource(applicationContext);
	}
	
	@Override
	public GerarchiaVo get(Integer id) {
		GerarchiaVo vo = null;
		Gerarchia entity = repo.getReferenceById(id);
		if(entity!=null) {
			vo = modelMapper.map(entity, GerarchiaVo.class);
		}
		return vo;
	}
	
	@Override
	public GerarchiaVo add(GerarchiaVo vo) {
		GerarchiaVo addedVo = null;
		if(vo!=null && vo.getId()==null) {
			Gerarchia entity = modelMapper.map(vo, Gerarchia.class);
			entity = repo.save(entity);
			addedVo = modelMapper.map(entity, GerarchiaVo.class);
		}
		return addedVo;
	}
	
	@Override
	public GerarchiaVo update(GerarchiaVo vo) {
		GerarchiaVo updatedVo = null;
		if(vo!=null && vo.getId()!=null) {
			Gerarchia entity = modelMapper.map(vo, Gerarchia.class);
			entity = repo.save(entity);
			updatedVo = modelMapper.map(entity, GerarchiaVo.class);
		}
		return updatedVo;
	}
	
	@Override
	public GerarchiaVo delete(Integer id) {
		GerarchiaVo deletedVo = null;
		if(repo.existsById(id)) {
			Gerarchia entity = repo.getReferenceById(id);
			if(entity!=null) {
				deletedVo = modelMapper.map(entity, GerarchiaVo.class);
				repo.delete(entity);
			}
		}
		return deletedVo;
	}
}
