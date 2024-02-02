package com.cex.application.service.authentication.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.Ruolo;
import com.cex.application.repository.authentication.RuoloRepository;
import com.cex.application.service.authentication.RuoloService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.RuoloVo;

@Service
public class RuoloServiceImpl implements RuoloService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RuoloRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<RuoloVo> getAll() {
		List<Ruolo> lista = repo.findAll();
		List<RuoloVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, RuoloVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public RuoloVo get(String id) {
		Ruolo entity = null;
		RuoloVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, RuoloVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}

	@Override
	public RuoloVo add(RuoloVo vo) {
		Ruolo entity = modelMapper.map(vo, Ruolo.class);
		entity = repo.save(entity);
		RuoloVo addedVo = modelMapper.map(entity, RuoloVo.class);
		return addedVo;
	}

	@Override
	public RuoloVo update(RuoloVo vo) {
		Ruolo entity = modelMapper.map(vo, Ruolo.class);
		RuoloVo updatedVo = null;
		if(repo.existsById(entity.getId())) {
			entity = repo.save(entity);
			updatedVo = modelMapper.map(entity, RuoloVo.class);
		} else {
			log.info("La risorsa che si intende aggiornare non esiste");
		}
		return updatedVo;
	}

	@Override
	public RuoloVo delete(String id) {
		RuoloVo vo = null;
		if(repo.existsById(id)) {
			Ruolo entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, RuoloVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
}
