package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cex.application.service.authentication.RuoloService;
import com.cex.application.vo.authentication.RuoloVo;

@RestController
@RequestMapping("/ruolo")
public class RuoloController 
{
	@Autowired
	private RuoloService service;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<RuoloVo> get(@PathVariable String id)
	{
		ResponseEntity<RuoloVo> response = null;
		RuoloVo extractedVo = service.get(id);
		if(extractedVo!=null) {
			response = new ResponseEntity<RuoloVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RuoloVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<RuoloVo>> getAll()
	{
		ResponseEntity<List<RuoloVo>> response = null;
		List<RuoloVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<RuoloVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<RuoloVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<RuoloVo> add(@RequestBody RuoloVo vo)
	{
		ResponseEntity<RuoloVo> response = null;
		RuoloVo addedVo = service.add(vo);
		response = new ResponseEntity<RuoloVo>(addedVo, HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<RuoloVo> update(@RequestBody RuoloVo vo)
	{
		ResponseEntity<RuoloVo> response = null;
		RuoloVo updateVo = service.update(vo);
		if(updateVo!=null) {
			response = new ResponseEntity<RuoloVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RuoloVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<RuoloVo> delete(@PathVariable String id)
	{
		ResponseEntity<RuoloVo> response = null;
		RuoloVo deletedVo = null;
		try {
			deletedVo = service.delete(id);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<RuoloVo>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(deletedVo!=null) {
			response = new ResponseEntity<RuoloVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RuoloVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
