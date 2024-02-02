package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cex.application.service.authentication.GerarchiaService;
import com.cex.application.vo.authentication.GerarchiaVo;

@RestController
@RequestMapping("/gerarchia")
public class GerarchiaController 
{
	@Autowired
	private GerarchiaService service;
	
	@GetMapping("/find-by-authority-type/{auth}")
	public ResponseEntity<List<GerarchiaVo>> findByAuthority(@PathVariable String auth)
	{
		ResponseEntity<List<GerarchiaVo>> response = null;
		List<GerarchiaVo> listaVo = service.findByAuthorityType(auth);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<GerarchiaVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<GerarchiaVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<GerarchiaVo>> getAll()
	{
		ResponseEntity<List<GerarchiaVo>> response = null;
		List<GerarchiaVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<GerarchiaVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<GerarchiaVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-hierarchy-source")
	public ResponseEntity<String> getHierarchySource()
	{
		ResponseEntity<String> response = null;
		String source = service.getHierarchySource();
		if(source!=null) {
			response = new ResponseEntity<String>(source, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-db-gerarchie-applicative")
	public ResponseEntity<List<String>> getDbGerarchieApplicative()
	{
		ResponseEntity<List<String>> response = null;
		List<String> lista = service.getDbGerarchieApplicative();
		if(lista!=null && !lista.isEmpty()) {
			response = new ResponseEntity<List<String>>(lista, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<GerarchiaVo> get(@PathVariable Integer id)
	{
		ResponseEntity<GerarchiaVo> response = null;
		GerarchiaVo vo = service.get(id);
		if(vo!=null) {
			response = new ResponseEntity<GerarchiaVo>(vo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<GerarchiaVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/insert")
	public ResponseEntity<GerarchiaVo> add(@RequestBody GerarchiaVo vo)
	{
		ResponseEntity<GerarchiaVo> response = null;
		GerarchiaVo addedVo = service.add(vo);
		if(addedVo!=null) {
			response = new ResponseEntity<GerarchiaVo>(addedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<GerarchiaVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<GerarchiaVo> update(@RequestBody GerarchiaVo vo)
	{
		ResponseEntity<GerarchiaVo> response = null;
		GerarchiaVo updatedVo = service.update(vo);
		if(updatedVo!=null) {
			response = new ResponseEntity<GerarchiaVo>(updatedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<GerarchiaVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GerarchiaVo> delete(@PathVariable Integer id)
	{
		ResponseEntity<GerarchiaVo> response = null;
		GerarchiaVo deletedVo = service.delete(id);
		if(deletedVo!=null) {
			response = new ResponseEntity<GerarchiaVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<GerarchiaVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
}
