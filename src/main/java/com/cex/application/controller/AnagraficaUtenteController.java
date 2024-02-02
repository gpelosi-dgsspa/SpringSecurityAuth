package com.cex.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cex.application.service.AnagraficaUtenteService;
import com.cex.application.vo.AnagraficaUtenteVo;

@RestController
@RequestMapping("/anagrafica-utente")
public class AnagraficaUtenteController 
{
	@Autowired
	private AnagraficaUtenteService service;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<AnagraficaUtenteVo> get(@PathVariable Long id)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo extractedVo = service.get(id);
		if(extractedVo!=null) {
			response = new ResponseEntity<AnagraficaUtenteVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<AnagraficaUtenteVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<AnagraficaUtenteVo>> getAll()
	{
		ResponseEntity<List<AnagraficaUtenteVo>> response = null;
		List<AnagraficaUtenteVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<AnagraficaUtenteVo> add(@RequestBody AnagraficaUtenteVo vo)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo addedVo = service.add(vo);
		response = new ResponseEntity<AnagraficaUtenteVo>(addedVo, HttpStatus.CREATED);
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<AnagraficaUtenteVo> update(@RequestBody AnagraficaUtenteVo vo)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo updateVo = service.update(vo);
		if(updateVo!=null) {
			response = new ResponseEntity<AnagraficaUtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<AnagraficaUtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/save-or-update")
	public ResponseEntity<AnagraficaUtenteVo> saveOrUpdate(@RequestBody AnagraficaUtenteVo vo)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo updateVo = service.saveOrUpdate(vo);
		if(updateVo!=null) {
			response = new ResponseEntity<AnagraficaUtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<AnagraficaUtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AnagraficaUtenteVo> delete(@PathVariable Long id)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo deletedVo = service.delete(id);
		if(deletedVo!=null) {
			response = new ResponseEntity<AnagraficaUtenteVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<AnagraficaUtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/find-by-id-utente/{idUtente}")
	public ResponseEntity<AnagraficaUtenteVo> findByIdUtente(@PathVariable Long idUtente)
	{
		ResponseEntity<AnagraficaUtenteVo> response = null;
		AnagraficaUtenteVo vo = service.findByIdUtente(idUtente);
		if(vo!=null) {
			response = new ResponseEntity<AnagraficaUtenteVo>(vo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<AnagraficaUtenteVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-codicefiscale/{cf}")
	public ResponseEntity<List<AnagraficaUtenteVo>> findByCodiceFiscale(@PathVariable String cf)
	{
		ResponseEntity<List<AnagraficaUtenteVo>> response = null;
		List<AnagraficaUtenteVo> listaVo = service.findByCodiceFiscale(cf);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-domicilio/{domicilio}")
	public ResponseEntity<List<AnagraficaUtenteVo>> findByDomicilio(@PathVariable String domicilio)
	{
		ResponseEntity<List<AnagraficaUtenteVo>> response = null;
		List<AnagraficaUtenteVo> listaVo = service.findByIndirizzo("%"+domicilio+"%");
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-criteria")
	public ResponseEntity<List<AnagraficaUtenteVo>> findByCriteria(@RequestBody AnagraficaUtenteVo vo)
	{
		ResponseEntity<List<AnagraficaUtenteVo>> response = null;
		List<AnagraficaUtenteVo> listaVo = service.findByCriteria(vo);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-example")
	public ResponseEntity<List<AnagraficaUtenteVo>> findByExample(@RequestBody AnagraficaUtenteVo vo)
	{
		ResponseEntity<List<AnagraficaUtenteVo>> response = null;
		List<AnagraficaUtenteVo> listaVo = service.findByExample(vo);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<AnagraficaUtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/test/{param}")
	public ResponseEntity<String> test(@PathVariable String param, @RequestParam(required = true) String parametro)
	{
		String msg = "PathParam: '" + param + "', RequestParam: '" + parametro + "'";
		ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
}
