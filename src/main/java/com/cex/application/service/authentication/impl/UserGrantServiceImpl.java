package com.cex.application.service.authentication.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cex.application.entity.authentication.UserGrant;
import com.cex.application.repository.authentication.UserGrantRepository;
import com.cex.application.service.authentication.UserGrantService;
import com.cex.application.util.AuthenticationUtil;
import com.cex.application.util.CollectableList;
import com.cex.application.util.EntityUtil;
import com.cex.application.vo.authentication.UserGrantVo;

@Service
public class UserGrantServiceImpl implements UserGrantService
{
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserGrantRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<UserGrantVo> findByUsername(String username) {
		List<UserGrant> lista = repo.findByUsername(username);
		List<UserGrantVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UserGrantVo.class, lista.toArray());
		}
		return listaVo;
	}
	
	@Override
	public List<String> findPagesByUsername(String username) 
	{
		CollectableList<String> listaPagineUtente = new CollectableList<String>();
		List<UserGrant> listaGrant = repo.findByUsername(username);
		String webPageSuffix = env.getProperty("spring.thymeleaf.suffix");
		List<String> listaPagineFull = AuthenticationUtil.getRootApplicationPages(webPageSuffix);
		
		for(UserGrant permesso: listaGrant) {
			for(String pagina: listaPagineFull) {
				if(permesso.getIdPermesso().toLowerCase().startsWith(pagina.toLowerCase())) {
					listaPagineUtente.collect(pagina);
				}
			}
		}
		return listaPagineUtente;
	}
	
	@Override
	public List<UserGrantVo> getAll() {
		List<UserGrant> lista = repo.findAll();
		List<UserGrantVo> listaVo = null;
		if(lista!=null && !lista.isEmpty()) {
			listaVo = EntityUtil.mapList(modelMapper, UserGrantVo.class, lista.toArray());
		}
		return listaVo;
	}
}
