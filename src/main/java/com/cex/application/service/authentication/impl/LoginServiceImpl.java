package com.cex.application.service.authentication.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.cex.application.config.authentication.AuthUserDetails;
import com.cex.application.service.authentication.LoginService;
import com.cex.application.vo.authentication.UserInfoVo;

@Service
public class LoginServiceImpl implements LoginService
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Override
	public List<String> getLoggedUsers() {
		return sessionRegistry.getAllPrincipals().stream()
			      .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
			      .map(AuthUserDetails::getUsername)
			      .collect(Collectors.toList());
	}
	
	@Override
	public List<UserInfoVo> getLoggedUsersInfo() {
		List<UserInfoVo> listaUtenti = new ArrayList<UserInfoVo>();
		try {
			List<Object> principals = sessionRegistry.getAllPrincipals();
			for(Object p: principals) {
				AuthUserDetails user = (AuthUserDetails)p;
				List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(p, false);
				for(SessionInformation s: sessionsInfo) {
					listaUtenti.add(new UserInfoVo(s.getSessionId(), user.getFullName(), user.getAccessDate()));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listaUtenti;
	}
	
	@Override
	public boolean forceLogout() {
		boolean esito = false;
		try {
			String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
			sessionRegistry.getSessionInformation(sessionId).expireNow();
			esito = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esito;
	}
	
	@Override
	public boolean forceAllLogout() {
		boolean esito = false;
		try {
			List<Object> principals = sessionRegistry.getAllPrincipals();
			for(Object p: principals) {
				List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(p, false);
				for(SessionInformation s: sessionsInfo) {
					s.expireNow();
				}
			}
			esito = true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esito;
	}
	
	@Override
	public boolean forceUserLogout(String username) {
		boolean esito = false;
		try {
			List<Object> principals = sessionRegistry.getAllPrincipals();
			for(Object p: principals) {
				AuthUserDetails user = (AuthUserDetails)p;
				List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(p, false);
				for(SessionInformation s: sessionsInfo) {
					if(username.equals(user.getFullName())) {
						s.expireNow();
						esito = true;
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return esito;
	}
	
	@Override
	public boolean forceMultiUserLogout(List<String> listaUtenti) {
		boolean esito = true;
		try {
			if(listaUtenti!=null) {
				for(String utente: listaUtenti) {
					boolean esitoLogout = forceUserLogout(utente);
					if(!esitoLogout) {
						esito = false;
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			esito = false;
		}
		return esito;
	}
}
