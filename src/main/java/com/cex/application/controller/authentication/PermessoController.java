package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.authentication.PermessoService;
import com.cex.application.vo.authentication.PermessoVo;

@RestController
@RequestMapping("/permesso")
public class PermessoController 
{
	@Autowired
	private PermessoService service;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PermessoVo> get(@PathVariable String id)
	{
		ResponseEntity<PermessoVo> response = null;
		PermessoVo extractedVo = service.get(id);
		if(extractedVo!=null) {
			response = new ResponseEntity<PermessoVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<PermessoVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<PermessoVo>> getAll()
	{
		ResponseEntity<List<PermessoVo>> response = null;
		List<PermessoVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<PermessoVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<PermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<PermessoVo> add(@RequestBody PermessoVo vo)
	{
		ResponseEntity<PermessoVo> response = null;
		if(service.exists(vo.getId())) {
			return new ResponseEntity<PermessoVo>(HttpStatus.NOT_ACCEPTABLE);
		}
		PermessoVo addedVo = service.add(vo);
		response = new ResponseEntity<PermessoVo>(addedVo, HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<PermessoVo> update(@RequestBody PermessoVo vo)
	{
		ResponseEntity<PermessoVo> response = null;
		PermessoVo updateVo = service.update(vo);
		if(updateVo!=null) {
			response = new ResponseEntity<PermessoVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<PermessoVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PermessoVo> delete(@PathVariable String id)
	{
		ResponseEntity<PermessoVo> response = null;
		PermessoVo deletedVo = null;
		try {
			deletedVo = service.delete(id);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<PermessoVo>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(deletedVo!=null) {
			response = new ResponseEntity<PermessoVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<PermessoVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
