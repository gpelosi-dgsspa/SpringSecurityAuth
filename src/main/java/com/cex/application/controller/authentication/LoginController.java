package com.cex.application.controller.authentication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.authentication.LoginService;
import com.cex.application.vo.authentication.UserInfoVo;

@RestController
@RequestMapping("/login")
public class LoginController 
{
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LoginService service;
	
	@GetMapping("/get-logged-users")
	public ResponseEntity<List<String>> getLoggedUsers()
	{
		List<String> listaUtentiLoggati = service.getLoggedUsers();
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(listaUtentiLoggati, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get-logged-users-info")
	public ResponseEntity<List<UserInfoVo>> getLoggedUsersInfo()
	{
		List<UserInfoVo> listaUtentiLoggati = service.getLoggedUsersInfo();
		ResponseEntity<List<UserInfoVo>> response = new ResponseEntity<List<UserInfoVo>>(listaUtentiLoggati, HttpStatus.OK);
		return response;
	}
	
	@PostMapping("/force-logout")
	public ResponseEntity<String> forceLogout() 
	{
		boolean esito = service.forceLogout();
		ResponseEntity<String> response = null;
		if(esito) {
			response = new ResponseEntity<String>("logout effettuato", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("utente non trovato", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PostMapping("/force-all-logout")
	public ResponseEntity<String> forceAllLogout() 
	{
		boolean esito = service.forceAllLogout();
		ResponseEntity<String> response = null;
		if(esito) {
			response = new ResponseEntity<String>("tutti logout effettuati", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("errore", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@PostMapping("/force-user-logout/{username}")
	public ResponseEntity<String> forceUserLogout(@PathVariable String username) 
	{
		ResponseEntity<String> response = null;
		boolean esito = service.forceUserLogout(username);
		if(esito) {
			response = new ResponseEntity<String>("logout " + username + " effettuato", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("utente non trovato", HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	@PostMapping("/force-multi-user-logout")
	public ResponseEntity<String> forceMultiUserLogout(@RequestBody List<String> listaUtenti) 
	{
		boolean esito = false;
		ResponseEntity<String> response = null;
		esito = service.forceMultiUserLogout(listaUtenti);
		if(esito) {
			response = new ResponseEntity<String>("logout effettuato", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("utente non trovato", HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
}
