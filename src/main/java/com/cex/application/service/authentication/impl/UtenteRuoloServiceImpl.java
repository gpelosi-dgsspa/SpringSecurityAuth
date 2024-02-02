package com.cex.application.service.authentication.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.UtenteRuolo;
import com.cex.application.entity.authentication.id.UtenteRuoloId;
import com.cex.application.repository.authentication.UtenteRuoloRepository;
import com.cex.application.service.authentication.UtenteRuoloService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.UtenteRuoloVo;

@Service
public class UtenteRuoloServiceImpl implements UtenteRuoloService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UtenteRuoloRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UtenteRuoloVo> getAll() {
		List<UtenteRuolo> lista = repo.findAll();
		List<UtenteRuoloVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtenteRuoloVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public UtenteRuoloVo get(Long idUtente, String idRuolo) {
		UtenteRuoloId id = new UtenteRuoloId(idUtente, idRuolo);
		UtenteRuolo entity = null;
		UtenteRuoloVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteRuoloVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}
	
	@Override
	public List<UtenteRuoloVo> findByUserId(Long idUtente) {
		UtenteRuolo entity = new UtenteRuolo();
		UtenteRuoloId entityId = new UtenteRuoloId();
		entityId.setIdUtente(idUtente);
		entity.setId(entityId);
		Example<UtenteRuolo> example = Example.of(entity);
		List<UtenteRuolo> lista = repo.findAll(example);
		List<UtenteRuoloVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtenteRuoloVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public UtenteRuoloVo add(UtenteRuoloVo vo) {
		UtenteRuolo entity = modelMapper.map(vo, UtenteRuolo.class);
		entity = repo.save(entity);
		UtenteRuoloVo addedVo = modelMapper.map(entity, UtenteRuoloVo.class);
		return addedVo;
	}

	@Override
	public UtenteRuoloVo delete(Long idUtente, String idRuolo) {
		UtenteRuoloId id = new UtenteRuoloId(idUtente, idRuolo);
		UtenteRuoloVo vo = null;
		if(repo.existsById(id)) {
			UtenteRuolo entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteRuoloVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public List<UtenteRuoloVo> deleteByUserId(Long idUtente) {
		UtenteRuolo entity = new UtenteRuolo();
		UtenteRuoloId entityId = new UtenteRuoloId();
		entityId.setIdUtente(idUtente);
		entity.setId(entityId);
		Example<UtenteRuolo> example = Example.of(entity);
		List<UtenteRuolo> lista = repo.findAll(example);
		List<UtenteRuoloVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtenteRuoloVo.class, lista.toArray());
			for(UtenteRuolo ur: lista) {
				repo.delete(ur);
			}
		}
		return listaVo;
	}
	
}
