package com.cex.application.service.authentication.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.Permesso;
import com.cex.application.repository.authentication.PermessoRepository;
import com.cex.application.service.authentication.PermessoService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.PermessoVo;

@Service
public class PermessoServiceImpl implements PermessoService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PermessoRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<PermessoVo> getAll() {
		List<Permesso> lista = repo.findAll();
		List<PermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, PermessoVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public PermessoVo get(String id) {
		Permesso entity = null;
		PermessoVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, PermessoVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}

	@Override
	public PermessoVo add(PermessoVo vo) {
		Permesso entity = modelMapper.map(vo, Permesso.class);
		entity = repo.save(entity);
		PermessoVo addedVo = modelMapper.map(entity, PermessoVo.class);
		return addedVo;
	}

	@Override
	public PermessoVo update(PermessoVo vo) {
		Permesso entity = modelMapper.map(vo, Permesso.class);
		PermessoVo updatedVo = null;
		if(repo.existsById(entity.getId())) {
			entity = repo.save(entity);
			updatedVo = modelMapper.map(entity, PermessoVo.class);
		} else {
			log.info("La risorsa che si intende aggiornare non esiste");
		}
		return updatedVo;
	}

	@Override
	public PermessoVo delete(String id) {
		PermessoVo vo = null;
		if(repo.existsById(id)) {
			Permesso entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, PermessoVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public boolean exists(String id) {
		return repo.existsById(id);
	}
	
}
