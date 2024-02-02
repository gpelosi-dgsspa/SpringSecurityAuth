package com.cex.application.controller.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.authentication.RuoloPermessoService;
import com.cex.application.vo.authentication.RuoloPermessoVo;

@RestController
@RequestMapping("/ruolo-permesso")
public class RuoloPermessoController 
{
	@Autowired
	private RuoloPermessoService service;
	
	@GetMapping("/get")
	public ResponseEntity<RuoloPermessoVo> get(@RequestParam(required = true) String ruolo, @RequestParam(required = true) String permesso)
	{
		ResponseEntity<RuoloPermessoVo> response = null;
		RuoloPermessoVo extractedVo = service.get(ruolo, permesso);
		if(extractedVo!=null) {
			response = new ResponseEntity<RuoloPermessoVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RuoloPermessoVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-ruolo/{idRuolo}")
	public ResponseEntity<List<RuoloPermessoVo>> findByRuolo(@PathVariable String idRuolo)
	{
		ResponseEntity<List<RuoloPermessoVo>> response = null;
		List<RuoloPermessoVo> listaVo = service.findByRuolo(idRuolo);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<RuoloPermessoVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<RuoloPermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<RuoloPermessoVo>> getAll()
	{
		ResponseEntity<List<RuoloPermessoVo>> response = null;
		List<RuoloPermessoVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<RuoloPermessoVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<RuoloPermessoVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<RuoloPermessoVo> add(@RequestBody RuoloPermessoVo vo)
	{
		ResponseEntity<RuoloPermessoVo> response = null;
		RuoloPermessoVo addedVo = service.add(vo);
		response = new ResponseEntity<RuoloPermessoVo>(addedVo, HttpStatus.CREATED);
		return response;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<RuoloPermessoVo> delete(@RequestParam(required = true) String ruolo, @RequestParam(required = true) String permesso)
	{
		ResponseEntity<RuoloPermessoVo> response = null;
		RuoloPermessoVo deletedVo = service.delete(ruolo, permesso);
		if(deletedVo!=null) {
			response = new ResponseEntity<RuoloPermessoVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<RuoloPermessoVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete-by-ruolo/{idRuolo}")
	public ResponseEntity<List<RuoloPermessoVo>> delete(@PathVariable String idRuolo)
	{
		ResponseEntity<List<RuoloPermessoVo>> response = null;
		List<RuoloPermessoVo> deletedList = service.deleteByRuolo(idRuolo);
		if(deletedList!=null && !deletedList.isEmpty()) {
			response = new ResponseEntity<List<RuoloPermessoVo>>(deletedList, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<RuoloPermessoVo>>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PostMapping("/save-multi/{idRuolo}")
	public ResponseEntity<List<RuoloPermessoVo>> addList(@PathVariable String idRuolo, @RequestBody List<RuoloPermessoVo> lista)
	{
		ResponseEntity<List<RuoloPermessoVo>> response = null;
		List<RuoloPermessoVo> listaAggiunti = service.deleteAndAddMultiple(idRuolo, lista);
		response = new ResponseEntity<List<RuoloPermessoVo>>(listaAggiunti, HttpStatus.OK);
		return response;
	}
	
}
