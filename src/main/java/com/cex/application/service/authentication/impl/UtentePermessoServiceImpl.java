package com.cex.application.service.authentication.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.UtentePermesso;
import com.cex.application.entity.authentication.id.UtentePermessoId;
import com.cex.application.repository.authentication.UtentePermessoRepository;
import com.cex.application.service.authentication.UtentePermessoService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.UtentePermessoVo;

@Service
public class UtentePermessoServiceImpl implements UtentePermessoService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UtentePermessoRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UtentePermessoVo> getAll() {
		List<UtentePermesso> lista = repo.findAll();
		List<UtentePermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtentePermessoVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public UtentePermessoVo get(Long idUtente, String idPermesso) {
		UtentePermessoId id = new UtentePermessoId(idUtente, idPermesso);
		UtentePermesso entity = null;
		UtentePermessoVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtentePermessoVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}
	
	@Override
	public List<UtentePermessoVo> findByUserId(Long idUtente) {
		UtentePermesso entity = new UtentePermesso();
		UtentePermessoId entityId = new UtentePermessoId();
		entityId.setIdUtente(idUtente);
		entity.setId(entityId);
		Example<UtentePermesso> example = Example.of(entity);
		List<UtentePermesso> lista = repo.findAll(example);
		List<UtentePermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtentePermessoVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public UtentePermessoVo add(UtentePermessoVo vo) {
		UtentePermesso entity = modelMapper.map(vo, UtentePermesso.class);
		entity = repo.save(entity);
		UtentePermessoVo addedVo = modelMapper.map(entity, UtentePermessoVo.class);
		return addedVo;
	}

	@Override
	public UtentePermessoVo delete(Long idUtente, String idPermesso) {
		UtentePermessoId id = new UtentePermessoId(idUtente, idPermesso);
		UtentePermessoVo vo = null;
		if(repo.existsById(id)) {
			UtentePermesso entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtentePermessoVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public List<UtentePermessoVo> deleteByUserId(Long idUtente) {
		UtentePermesso entity = new UtentePermesso();
		UtentePermessoId entityId = new UtentePermessoId();
		entityId.setIdUtente(idUtente);
		entity.setId(entityId);
		Example<UtentePermesso> example = Example.of(entity);
		List<UtentePermesso> lista = repo.findAll(example);
		List<UtentePermessoVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtentePermessoVo.class, lista.toArray());
			for(UtentePermesso up: lista) {
				repo.delete(up);
			}
		}
		return listaVo;
	}
	
}
