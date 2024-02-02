package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cex.application.service.authentication.UtenteRuoloService;
import com.cex.application.vo.authentication.UtenteRuoloVo;

@RestController
@RequestMapping("/utente-ruolo")
public class UtenteRuoloController 
{
	@Autowired
	private UtenteRuoloService service;
	
	@GetMapping("/get")
	public ResponseEntity<UtenteRuoloVo> get(@RequestParam(required = true) Long utente, @RequestParam(required = true) String ruolo)
	{
		ResponseEntity<UtenteRuoloVo> response = null;
		UtenteRuoloVo extractedVo = service.get(utente, ruolo);
		if(extractedVo!=null) {
			response = new ResponseEntity<UtenteRuoloVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteRuoloVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<UtenteRuoloVo>> getAll()
	{
		ResponseEntity<List<UtenteRuoloVo>> response = null;
		List<UtenteRuoloVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtenteRuoloVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtenteRuoloVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-userid/{id}")
	public ResponseEntity<List<UtenteRuoloVo>> findByUserId(@PathVariable Long id)
	{
		ResponseEntity<List<UtenteRuoloVo>> response = null;
		List<UtenteRuoloVo> listaVo = service.findByUserId(id);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtenteRuoloVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtenteRuoloVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<UtenteRuoloVo> add(@RequestBody UtenteRuoloVo vo)
	{
		ResponseEntity<UtenteRuoloVo> response = null;
		UtenteRuoloVo addedVo = service.add(vo);
		if(addedVo!=null) {
			response = new ResponseEntity<UtenteRuoloVo>(addedVo, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<UtenteRuoloVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<UtenteRuoloVo> delete(@RequestParam(required = true) Long utente, @RequestParam(required = true) String ruolo)
	{
		ResponseEntity<UtenteRuoloVo> response = null;
		UtenteRuoloVo deletedVo = service.delete(utente, ruolo);
		if(deletedVo!=null) {
			response = new ResponseEntity<UtenteRuoloVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteRuoloVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@DeleteMapping("/delete-by-userid/{id}")
	public ResponseEntity<List<UtenteRuoloVo>> deleteByUserId(@PathVariable Long id)
	{
		ResponseEntity<List<UtenteRuoloVo>> response = null;
		List<UtenteRuoloVo> deletedVo = service.deleteByUserId(id);
		if(deletedVo!=null && !deletedVo.isEmpty()) {
			response = new ResponseEntity<List<UtenteRuoloVo>>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtenteRuoloVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
}
