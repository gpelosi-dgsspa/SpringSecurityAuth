package com.cex.application.service.authentication.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.RuoloPermesso;
import com.cex.application.entity.authentication.id.RuoloPermessoId;
import com.cex.application.repository.authentication.RuoloPermessoRepository;
import com.cex.application.service.authentication.RuoloPermessoService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.RuoloPermessoVo;

@Service
public class RuoloPermessoServiceImpl implements RuoloPermessoService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RuoloPermessoRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<RuoloPermessoVo> getAll() {
		List<RuoloPermesso> lista = repo.findAll();
		List<RuoloPermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, RuoloPermessoVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public RuoloPermessoVo get(String idRuolo, String idPermesso) {
		RuoloPermessoId id = new RuoloPermessoId(idRuolo, idPermesso);
		RuoloPermesso entity = null;
		RuoloPermessoVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, RuoloPermessoVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}
	
	@Override
	public List<RuoloPermessoVo> findByRuolo(String idRuolo) {
		RuoloPermesso entity = new RuoloPermesso();
		RuoloPermessoId idEntity = new RuoloPermessoId();
		idEntity.setIdRuolo(idRuolo);
		entity.setId(idEntity);
		Example<RuoloPermesso> example = Example.of(entity);
		List<RuoloPermesso> lista = repo.findAll(example);
		List<RuoloPermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, RuoloPermessoVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public RuoloPermessoVo add(RuoloPermessoVo vo) {
		RuoloPermesso entity = modelMapper.map(vo, RuoloPermesso.class);
		entity = repo.save(entity);
		RuoloPermessoVo addedVo = modelMapper.map(entity, RuoloPermessoVo.class);
		return addedVo;
	}

	@Override
	public RuoloPermessoVo delete(String idRuolo, String idPermesso) {
		RuoloPermessoId id = new RuoloPermessoId(idRuolo, idPermesso);
		RuoloPermessoVo vo = null;
		if(repo.existsById(id)) {
			RuoloPermesso entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, RuoloPermessoVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public List<RuoloPermessoVo> deleteByRuolo(String idRuolo) {
		RuoloPermesso entity = new RuoloPermesso();
		RuoloPermessoId idEntity = new RuoloPermessoId();
		idEntity.setIdRuolo(idRuolo);
		entity.setId(idEntity);
		Example<RuoloPermesso> example = Example.of(entity);
		List<RuoloPermesso> lista = repo.findAll(example);
		List<RuoloPermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, RuoloPermessoVo.class, lista.toArray());
			for(RuoloPermesso rp: lista) {
				repo.delete(rp);
			}
		}
		return listaVo;
	}
	
	@Override
	public List<RuoloPermessoVo> deleteAndAddMultiple(String idRuolo, List<RuoloPermessoVo> listaVo) {
		List<RuoloPermessoVo> listaVoAggiunti = new ArrayList<RuoloPermessoVo>();;
		try {
			deleteByRuolo(idRuolo);
			if(listaVo!=null && !listaVo.isEmpty()) {
				for(RuoloPermessoVo vo: listaVo) {
					RuoloPermessoVo voAggiunto = add(vo);
					listaVoAggiunti.add(voAggiunto);
				}
			}
		} catch (Exception e) {
			log.error("Errore nella assegnazione dei permessi al ruolo");
		}
		return listaVoAggiunti;
	}
}
