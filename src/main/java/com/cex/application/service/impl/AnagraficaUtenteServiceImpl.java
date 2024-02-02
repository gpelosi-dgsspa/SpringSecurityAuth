package com.cex.application.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cex.application.entity.AnagraficaUtente;
import com.cex.application.repository.AnagraficaUtenteRepository;
import com.cex.application.service.AnagraficaUtenteService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.AnagraficaUtenteVo;

@Service
public class AnagraficaUtenteServiceImpl implements AnagraficaUtenteService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AnagraficaUtenteRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<AnagraficaUtenteVo> getAll() {
		List<AnagraficaUtente> lista = repo.findAll();
		List<AnagraficaUtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, AnagraficaUtenteVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public AnagraficaUtenteVo get(Long id) {
		AnagraficaUtente entity = null;
		AnagraficaUtenteVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, AnagraficaUtenteVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}

	@Override
	public AnagraficaUtenteVo add(AnagraficaUtenteVo vo) {
		AnagraficaUtente entity = modelMapper.map(vo, AnagraficaUtente.class);
		if(!repo.existsById(entity.getId())) {
			entity = repo.save(entity);
		} else {
			entity = null;
		}
		AnagraficaUtenteVo addedVo = modelMapper.map(entity, AnagraficaUtenteVo.class);
		return addedVo;
	}

	@Override
	public AnagraficaUtenteVo update(AnagraficaUtenteVo vo) {
		AnagraficaUtente entity = modelMapper.map(vo, AnagraficaUtente.class);
		AnagraficaUtenteVo updatedVo = null;
		if(repo.existsById(entity.getId())) {
			entity = repo.save(entity);
			updatedVo = modelMapper.map(entity, AnagraficaUtenteVo.class);
		} else {
			log.info("La risorsa che si intende aggiornare non esiste");
		}
		return updatedVo;
	}
	
	@Override
	public AnagraficaUtenteVo saveOrUpdate(AnagraficaUtenteVo vo) {
		AnagraficaUtente entity = modelMapper.map(vo, AnagraficaUtente.class);
		entity = repo.save(entity);
		AnagraficaUtenteVo updatedVo = modelMapper.map(entity, AnagraficaUtenteVo.class);
		return updatedVo;
	}

	@Override
	public AnagraficaUtenteVo delete(Long id) {
		AnagraficaUtenteVo vo = null;
		if(repo.existsById(id)) {
			AnagraficaUtente entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, AnagraficaUtenteVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public AnagraficaUtenteVo findByIdUtente(Long idUtente) {
		AnagraficaUtente entity = repo.findByIdUtente(idUtente);
		AnagraficaUtenteVo vo = null;
		if(entity!=null) {
			vo = modelMapper.map(entity, AnagraficaUtenteVo.class);
		}
		return vo;
	}
	
	@Override
	public List<AnagraficaUtenteVo> findByCriteria(AnagraficaUtenteVo vo) {
		AnagraficaUtente entity = modelMapper.map(vo, AnagraficaUtente.class);
		List<AnagraficaUtente> lista = repo.findByCriteria(entity);
		List<AnagraficaUtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, AnagraficaUtenteVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	public List<AnagraficaUtenteVo> findByExample(AnagraficaUtenteVo vo) {
		AnagraficaUtente entity = modelMapper.map(vo, AnagraficaUtente.class);
		Example<AnagraficaUtente> example = Example.of(entity);
		List<AnagraficaUtente> lista = repo.findAll(example);
		List<AnagraficaUtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, AnagraficaUtenteVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public List<AnagraficaUtenteVo> findByCodiceFiscale(String cf) {
		List<AnagraficaUtente> lista = repo.findByCodiceFiscale(cf);
		List<AnagraficaUtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, AnagraficaUtenteVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	public List<AnagraficaUtenteVo> findByIndirizzo(String indirizzo) {
		List<AnagraficaUtente> lista = repo.findByIndirizzoLikeIgnoreCase(indirizzo);
		List<AnagraficaUtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, AnagraficaUtenteVo.class, lista.toArray());
		}
		return listaVo;
	}
	
}
