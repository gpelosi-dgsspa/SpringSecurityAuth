package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cex.application.service.authentication.UtentePermessoService;
import com.cex.application.vo.authentication.UtentePermessoVo;

@RestController
@RequestMapping("/utente-permesso")
public class UtentePermessoController 
{
	@Autowired
	private UtentePermessoService service;
	
	@GetMapping("/get")
	public ResponseEntity<UtentePermessoVo> get(@RequestParam(required = true) Long utente, @RequestParam(required = true) String permesso)
	{
		ResponseEntity<UtentePermessoVo> response = null;
		UtentePermessoVo extractedVo = service.get(utente, permesso);
		if(extractedVo!=null) {
			response = new ResponseEntity<UtentePermessoVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtentePermessoVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<UtentePermessoVo>> getAll()
	{
		ResponseEntity<List<UtentePermessoVo>> response = null;
		List<UtentePermessoVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtentePermessoVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtentePermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-userid/{id}")
	public ResponseEntity<List<UtentePermessoVo>> findByUserId(@PathVariable Long id)
	{
		ResponseEntity<List<UtentePermessoVo>> response = null;
		List<UtentePermessoVo> listaVo = service.findByUserId(id);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtentePermessoVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtentePermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<UtentePermessoVo> add(@RequestBody UtentePermessoVo vo)
	{
		ResponseEntity<UtentePermessoVo> response = null;
		UtentePermessoVo addedVo = service.add(vo);
		if(addedVo!=null) {
			response = new ResponseEntity<UtentePermessoVo>(addedVo, HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<UtentePermessoVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<UtentePermessoVo> delete(@RequestParam(required = true) Long utente, @RequestParam(required = true) String permesso)
	{
		ResponseEntity<UtentePermessoVo> response = null;
		UtentePermessoVo deletedVo = service.delete(utente, permesso);
		if(deletedVo!=null) {
			response = new ResponseEntity<UtentePermessoVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtentePermessoVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@DeleteMapping("/delete-by-userid/{id}")
	public ResponseEntity<List<UtentePermessoVo>> deleteByUserId(@PathVariable Long id)
	{
		ResponseEntity<List<UtentePermessoVo>> response = null;
		List<UtentePermessoVo> deletedVoList = service.deleteByUserId(id);
		if(deletedVoList!=null && !deletedVoList.isEmpty()) {
			response = new ResponseEntity<List<UtentePermessoVo>>(deletedVoList, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtentePermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
