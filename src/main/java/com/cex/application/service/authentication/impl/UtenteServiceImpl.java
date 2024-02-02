package com.cex.application.service.authentication.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cex.application.config.properties.ConfigProperties;
import com.cex.application.entity.AnagraficaUtente;
import com.cex.application.entity.authentication.Utente;
import com.cex.application.entity.authentication.UtentePermesso;
import com.cex.application.entity.authentication.UtenteRuolo;
import com.cex.application.entity.authentication.id.UtentePermessoId;
import com.cex.application.entity.authentication.id.UtenteRuoloId;
import com.cex.application.repository.AnagraficaUtenteRepository;
import com.cex.application.repository.authentication.UtentePermessoRepository;
import com.cex.application.repository.authentication.UtenteRepository;
import com.cex.application.repository.authentication.UtenteRuoloRepository;
import com.cex.application.service.authentication.UtenteService;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.UtenteExtendedVo;
import com.cex.application.vo.authentication.UtenteVo;

import jakarta.annotation.Resource;

@Service
@PropertySource("classpath:config.properties")
public class UtenteServiceImpl implements UtenteService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String rolePrefix = "ROLE_";
	
	@Autowired
	private UtenteRepository repo;
	
	@Autowired
	private AnagraficaUtenteRepository anagRepo;
	
	@Autowired
	private UtentePermessoRepository utentePermessoRepo;
	
	@Autowired
	private UtenteRuoloRepository utenteRuoloRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Resource
	private RoleHierarchy roleHierarchy;
	
	@Override
	public List<UtenteVo> getAll() {
		List<Utente> lista = repo.findAll();
		List<UtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtenteVo.class, lista.toArray());
		}
		return listaVo;
	}

	@Override
	public UtenteVo get(Long id) {
		Utente entity = null;
		UtenteVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}
	
	@Override
	public UtenteExtendedVo getExtended(Long id) {
		Utente entity = null;
		UtenteExtendedVo vo = null;
		if(repo.existsById(id)) {
			entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteExtendedVo.class);
			
			AnagraficaUtente anag = anagRepo.findByIdUtente(id);
			if(anag!=null) {
				vo.setNome(anag.getNome());
				vo.setCognome(anag.getCognome());
			}
		} else {
			log.info("Risorsa non trovata");
		}
		return vo;
	}
	
	@Override
	public String getCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return username;
	}
	
	@Override
	public UtenteVo getFullCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return findByUsername(username);
	}
	
	@Override
	public UtenteExtendedVo getFullExtendedCurrent() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return findExtendedByUsername(username);
	}
	
	@Override
	public boolean hasHierarchyAuthority(String auth)
	{
        Collection<? extends GrantedAuthority> hierarchyAuthorities = getTotalHierarchyAuthorities();
        for (GrantedAuthority authority : hierarchyAuthorities) {
            if (authority.getAuthority().equals(auth)) {
                return true;
            }
        }
        return false;
    }
	
	@Override
	public List<String> getAllHierarchyAuthorities()
	{
		List<String> listaAuth = new ArrayList<String>();
        Collection<? extends GrantedAuthority> hierarchyAuthorities = getTotalHierarchyAuthorities();
        for(GrantedAuthority ga: hierarchyAuthorities) {
        	listaAuth.add(ga.getAuthority());
        }
        return listaAuth;
	}
	
	@Override
	public List<String> getHierarchyAuthorities()
	{
		List<String> listaAuth = new ArrayList<String>();
        Collection<? extends GrantedAuthority> hierarchyAuthorities = getTotalHierarchyAuthorities();
        for(GrantedAuthority ga: hierarchyAuthorities) {
        	String authority = ga.getAuthority();
        	if(!authority.startsWith(rolePrefix)) {
        		listaAuth.add(ga.getAuthority());
        	}
        }
        return listaAuth;
	}
	
	@Override
	public List<String> getHierarchyRoles()
	{
		List<String> listaAuth = new ArrayList<String>();
        Collection<? extends GrantedAuthority> hierarchyAuthorities = getTotalHierarchyAuthorities();
        for(GrantedAuthority ga: hierarchyAuthorities) {
        	String authority = ga.getAuthority();
        	if(authority.startsWith(rolePrefix)) {
        		listaAuth.add(ga.getAuthority().substring(rolePrefix.length()));
        	}
        }
        return listaAuth;
	}
	
	@Override
	public UtenteVo add(UtenteVo vo) {
		Utente entity = modelMapper.map(vo, Utente.class);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		entity.setPassword(crypt.encode(entity.getPassword()));
		entity = repo.save(entity);
		UtenteVo addedVo = modelMapper.map(entity, UtenteVo.class);
		return addedVo;
	}
	
	@Override
	public UtenteVo register(UtenteVo vo) 
	{
		vo.setCreationDate(new Date());
		vo.setLocked(Boolean.valueOf(false));
		vo.setEnabled(Boolean.valueOf(false));
		
		Utente entity = modelMapper.map(vo, Utente.class);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		entity.setPassword(crypt.encode(entity.getPassword()));
		entity = repo.save(entity);
		UtenteVo addedVo = modelMapper.map(entity, UtenteVo.class);
		return addedVo;
	}

	@Override
	public UtenteVo update(UtenteVo vo) 
	{
		Utente entity = modelMapper.map(vo, Utente.class);
		UtenteVo updatedVo = null;
		if(repo.existsById(entity.getId())) {
			Utente entityDb = repo.getReferenceById(entity.getId());
			if(entity.getPassword()!=null && !entity.getPassword().trim().equals("")) {
				BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
				entity.setPassword(crypt.encode(entity.getPassword()));
			} else {
				entity.setPassword(entityDb.getPassword());
			}
			
			try {
				entity = repo.save(entity);
			} catch (DataIntegrityViolationException e) {
				entity = null;
				log.error(e.getMessage());
			}
			
			if(entity!=null) {
				updatedVo = modelMapper.map(entity, UtenteVo.class);
			}
		} else {
			log.info("La risorsa che si intende aggiornare non esiste");
		}
		return updatedVo;
	}

	@Override
	public UtenteVo delete(Long id) {
		UtenteVo vo = null;
		if(repo.existsById(id)) {
			Utente entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteVo.class);
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public UtenteVo deleteCascade(Long id) {
		UtenteVo vo = null;
		if(repo.existsById(id)) {
			Utente entity = repo.getReferenceById(id);
			vo = modelMapper.map(entity, UtenteVo.class);
			
			UtentePermessoId upId = new UtentePermessoId();
			upId.setIdUtente(id);
			UtentePermesso upEntity = new UtentePermesso();
			upEntity.setId(upId);
			
			Example<UtentePermesso> exampleUP = Example.of(upEntity);
			List<UtentePermesso> listaUP = utentePermessoRepo.findAll(exampleUP);
			if(listaUP!=null && !listaUP.isEmpty()) {
				for(UtentePermesso up: listaUP) {
					utentePermessoRepo.delete(up);
				}
			}
			
			UtenteRuoloId urId = new UtenteRuoloId();
			urId.setIdUtente(id);
			UtenteRuolo urEntity = new UtenteRuolo();
			urEntity.setId(urId);
			
			Example<UtenteRuolo> exampleUR = Example.of(urEntity);
			List<UtenteRuolo> listaUR = utenteRuoloRepo.findAll(exampleUR);
			if(listaUR!=null && !listaUR.isEmpty()) {
				for(UtenteRuolo ur: listaUR) {
					utenteRuoloRepo.delete(ur);
				}
			}
			
			repo.delete(entity);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public UtenteVo deleteByUsername(String username) {
		UtenteVo vo = null;
		Utente utente = repo.findByUsername(username);
		if(utente!=null) {
			vo = modelMapper.map(utente, UtenteVo.class);
			repo.delete(utente);
		} else {
			log.info("La risorsa che si intende eliminare non esiste");
		}
		return vo;
	}
	
	@Override
	public UtenteVo findByUsername(String username) {
		Utente utente = repo.findByUsername(username);
		UtenteVo vo = null;
		if(utente!=null) {
			vo = modelMapper.map(utente, UtenteVo.class);
		}
		return vo;
	}
	
	@Override
	public UtenteExtendedVo findExtendedByUsername(String username) {
		Utente utente = repo.findByUsername(username);
		UtenteExtendedVo vo = null;
		if(utente!=null) {
			vo = modelMapper.map(utente, UtenteExtendedVo.class);
			
			AnagraficaUtente anag = anagRepo.findByIdUtente(vo.getId());
			if(anag!=null) {
				vo.setNome(anag.getNome());
				vo.setCognome(anag.getCognome());
			}
		}
		return vo;
	}
	
	@Override
	public List<UtenteVo> findByExample(UtenteVo vo) {
		Utente entity = modelMapper.map(vo, Utente.class);
		Example<Utente> example = Example.of(entity);
		List<Utente> lista = repo.findAll(example);
		List<UtenteVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UtenteVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	@Transactional
	public String resetPasswordByUsername(String username) {
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		
		String password = null;
		if(cp.getRandomInitialPassword()) {
			password = RandomStringUtils.random(16, true, true);
		} else {
			password = cp.getInitialPassword();
		}
		
		Utente entity = repo.changePasswordByUsername(username, crypt.encode(password));
		if(entity==null) {
			log.info("La risorsa di cui si vuole cambiare la password non esiste");
		}
		return password;
	}
	
	@Override
	@Transactional
	public UtenteVo changePasswordByUsername(String username, String password) {
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		Utente entity = repo.changePasswordByUsername(username, crypt.encode(password));
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa di cui si vuole cambiare la password non esiste");
		}
		return updatedVo;
	}
	
	@Override
	@Transactional
	public UtenteVo lockByUsername(String username) {
		Utente entity = repo.lockByUsername(username);
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa che si intende bloccare non esiste");
		}
		return updatedVo;
	}
	
	@Override
	@Transactional
	public UtenteVo unlockByUsername(String username) {
		Utente entity = repo.unlockByUsername(username);
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa che si intende sbloccare non esiste");
		}
		return updatedVo;
	}
	
	@Override
	@Transactional
	public UtenteVo disableByUsername(String username) {
		Utente entity = repo.disableByUsername(username);
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa che si intende disabilitare non esiste");
		}
		return updatedVo;
	}
	
	@Override
	@Transactional
	public UtenteVo enableByUsername(String username) {
		Utente entity = repo.enableByUsername(username);
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa che si intende disabilitare non esiste");
		}
		return updatedVo;
	}
	
	@Override
	@Transactional
	public UtenteVo expireByUsername(String username, Date expire) {
		Utente entity = repo.expireByUsername(username, expire);
		UtenteVo updatedVo = null;
		if(entity!=null) {
			updatedVo = modelMapper.map(entity, UtenteVo.class);
		} else {
			log.info("La risorsa che si intende bloccare non esiste");
		}
		return updatedVo;
	}
	
	private Collection<? extends GrantedAuthority> getTotalHierarchyAuthorities() {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Collection<? extends GrantedAuthority> hierarchyAuthorities = roleHierarchy.getReachableGrantedAuthorities(authorities);
        return hierarchyAuthorities;
	}
}
