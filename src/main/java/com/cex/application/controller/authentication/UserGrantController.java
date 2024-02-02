package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.authentication.UserGrantService;
import com.cex.application.vo.authentication.UserGrantVo;

@RestController
@RequestMapping("/user-grant")
public class UserGrantController 
{
	@Autowired
	private UserGrantService service;
	
	@GetMapping("/find-by-username/{username}")
	public ResponseEntity<List<UserGrantVo>> findByUsername(@PathVariable String username)
	{
		ResponseEntity<List<UserGrantVo>> response = null;
		List<UserGrantVo> listaVo = service.findByUsername(username);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UserGrantVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UserGrantVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-pages-by-username/{username}")
	public ResponseEntity<List<String>> findPagesByUsername(@PathVariable String username)
	{
		ResponseEntity<List<String>> response = null;
		List<String> listaPagine = service.findPagesByUsername(username);
		response = new ResponseEntity<List<String>>(listaPagine, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<UserGrantVo>> getAll()
	{
		ResponseEntity<List<UserGrantVo>> response = null;
		List<UserGrantVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UserGrantVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UserGrantVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
}
