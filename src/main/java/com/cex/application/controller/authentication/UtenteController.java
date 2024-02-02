package com.cex.application.controller.authentication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.authentication.UtenteService;
import com.cex.application.vo.UtenteExtendedVo;
import com.cex.application.vo.authentication.UtenteVo;

@RestController
@RequestMapping("/utente")
public class UtenteController 
{
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UtenteService service;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UtenteVo> get(@PathVariable Long id)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo extractedVo = service.get(id);
		if(extractedVo!=null) {
			response = new ResponseEntity<UtenteVo>(extractedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("/get-current")
	public ResponseEntity<String> getCurrent()
	{
		String username = service.getCurrent();
		ResponseEntity<String> response = null;
		if(username!=null && !username.equals("")) {
			response = new ResponseEntity<String>(username, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("", HttpStatus.OK);
		}
		return response;
	}
	
	@GetMapping("/get-full-current")
	public ResponseEntity<UtenteVo> getFullCurrent()
	{
		UtenteVo user = service.getFullCurrent();
		ResponseEntity<UtenteVo> response = null;
		if(user!=null) {
			response = new ResponseEntity<UtenteVo>(user, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/get-full-extended-current")
	public ResponseEntity<UtenteExtendedVo> getExtendedFullCurrent()
	{
		UtenteExtendedVo user = service.getFullExtendedCurrent();
		ResponseEntity<UtenteExtendedVo> response = null;
		if(user!=null) {
			response = new ResponseEntity<UtenteExtendedVo>(user, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteExtendedVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/has-hierarchy-authority/{auth}")
	public ResponseEntity<Boolean> hasHierarchyAuthority(@PathVariable String auth) //, RoleHierarchy roleHierarchy 
	{
		boolean esito = service.hasHierarchyAuthority(auth);
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(Boolean.valueOf(esito), HttpStatus.OK);
        return response;
    }
	
	@GetMapping("/get-all-hierarchy-authorities")
	public ResponseEntity<List<String>> getHierarchyAllAuthorities()
	{
		ResponseEntity<List<String>> response = null;
        List<String> listaAuth = service.getAllHierarchyAuthorities();
        if(!listaAuth.isEmpty()) {
        	response = new ResponseEntity<List<String>>(listaAuth, HttpStatus.OK);
        } else {
        	response = new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        }
        return response;
    }
	
	@GetMapping("/get-hierarchy-authorities")
	public ResponseEntity<List<String>> getHierarchyAuthorities()
	{
		ResponseEntity<List<String>> response = null;
        List<String> listaAuth = service.getHierarchyAuthorities();
        if(!listaAuth.isEmpty()) {
        	response = new ResponseEntity<List<String>>(listaAuth, HttpStatus.OK);
        } else {
        	response = new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        }
        return response;
    }
	
	@GetMapping("/get-hierarchy-roles")
	public ResponseEntity<List<String>> getHierarchyRoles()
	{
		ResponseEntity<List<String>> response = null;
        List<String> listaAuth = service.getHierarchyRoles();
        if(!listaAuth.isEmpty()) {
        	response = new ResponseEntity<List<String>>(listaAuth, HttpStatus.OK);
        } else {
        	response = new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        }
        return response;
    }
	
	@GetMapping("/get-all")
	public ResponseEntity<List<UtenteVo>> getAll()
	{
		ResponseEntity<List<UtenteVo>> response = null;
		List<UtenteVo> listaVo = service.getAll();
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PostMapping("/save")
	public ResponseEntity<UtenteVo> add(@RequestBody UtenteVo vo)
	{
		ResponseEntity<UtenteVo> response = null;
		String username = vo.getUsername();
		UtenteVo voExists = service.findByUsername(username);
		if(voExists!=null) {
			response = new ResponseEntity<UtenteVo>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			UtenteVo addedVo = service.add(vo);
			response = new ResponseEntity<UtenteVo>(addedVo, HttpStatus.CREATED);
			
//			gestione del path dell'uri nella header location
//			URI location = ServletUriComponentsBuilder
////                  .fromCurrentRequest()
//                  .fromCurrentContextPath()
////                  .path("/{id}")
//                  .path("/utente/get/{id}")
//                  .buildAndExpand(addedVo.getId())
//                  .toUri();
//			response = ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location.toString()).build();
		}
		return response;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UtenteVo> register(@RequestBody UtenteVo vo)
	{
		ResponseEntity<UtenteVo> response = null;
		String username = vo.getUsername();
		UtenteVo voExists = service.findByUsername(username);
		if(voExists!=null) {
			response = new ResponseEntity<UtenteVo>(HttpStatus.NOT_ACCEPTABLE);
		} else {
			UtenteVo addedVo = service.register(vo);
			response = new ResponseEntity<UtenteVo>(addedVo, HttpStatus.CREATED);
		}
		return response;
	}
	
	@PutMapping("/update")
	public ResponseEntity<UtenteVo> update(@RequestBody UtenteVo vo)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.update(vo);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UtenteVo> delete(@PathVariable Long id)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo deletedVo = service.delete(id);
		if(deletedVo!=null) {
			response = new ResponseEntity<UtenteVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete-cascade/{id}")
	public ResponseEntity<UtenteVo> deleteCascade(@PathVariable Long id)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo deletedVo = null;
		boolean pkViolata = false;
		try {
			deletedVo = service.deleteCascade(id);
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException) {
				pkViolata = true;
			}
		}
		
		if(deletedVo!=null) {
			response = new ResponseEntity<UtenteVo>(deletedVo, HttpStatus.OK);
		} else if(pkViolata) {
			response = new ResponseEntity<UtenteVo>(HttpStatus.LOCKED);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/delete-by-username/{username}")
	public ResponseEntity<UtenteVo> deleteByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo deletedVo = service.deleteByUsername(username);
		if(deletedVo!=null) {
			response = new ResponseEntity<UtenteVo>(deletedVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/find-by-username/{username}")
	public ResponseEntity<UtenteVo> findByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo vo = service.findByUsername(username);
		if(vo!=null) {
			response = new ResponseEntity<UtenteVo>(vo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-extended-by-username/{username}")
	public ResponseEntity<UtenteExtendedVo> findExtendedByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteExtendedVo> response = null;
		UtenteExtendedVo vo = service.findExtendedByUsername(username);
		if(vo!=null) {
			response = new ResponseEntity<UtenteExtendedVo>(vo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteExtendedVo>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@GetMapping("/find-by-example")
	public ResponseEntity<List<UtenteVo>> findByExample(@RequestBody UtenteVo vo)
	{
		ResponseEntity<List<UtenteVo>> response = null;
		List<UtenteVo> listaVo = service.findByExample(vo);
		if(listaVo!=null && !listaVo.isEmpty()) {
			response = new ResponseEntity<List<UtenteVo>>(listaVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<UtenteVo>>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
	
	@PutMapping("/reset-password-by-username/{username}")
	public ResponseEntity<String> resetPasswordByUsername(@PathVariable String username)
	{
		ResponseEntity<String> response = null;
		String pwd = service.resetPasswordByUsername(username);
		if(pwd!=null) {
			response = new ResponseEntity<String>(pwd, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/change-password-by-username/{username}")
	public ResponseEntity<UtenteVo> changePasswordByUsername(@PathVariable(required = true) String username, @RequestParam(required = true) String oldpassword, @RequestParam(required = true) String newpassword)
	{
//		ResponseEntity<UtenteVo> response = null;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UtenteVo updateVo = service.findByUsername(username);
		if(updateVo==null) {
			return new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		boolean match = encoder.matches(oldpassword, updateVo.getPassword());
		if(!match) {
			return new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		updateVo = service.changePasswordByUsername(username, newpassword);
		if(updateVo!=null) {
			return new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			return new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
//		return response;
	}
	
	@PutMapping("/lock-by-username/{username}")
	public ResponseEntity<UtenteVo> lockByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.lockByUsername(username);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/unlock-by-username/{username}")
	public ResponseEntity<UtenteVo> unlockByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.unlockByUsername(username);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/disable-by-username/{username}")
	public ResponseEntity<UtenteVo> disableByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.disableByUsername(username);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/enable-by-username/{username}")
	public ResponseEntity<UtenteVo> enableByUsername(@PathVariable String username)
	{
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.enableByUsername(username);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping("/expire-by-username/{username}")
	public ResponseEntity<UtenteVo> expireByUsername(@PathVariable String username, @RequestParam(required = false) String expire)
	{
		Date expireDate = null;
		if(expire!=null && !expire.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				expireDate = sdf.parse(expire);
			} catch (ParseException e) {
				log.error("Errore nella conversione della data");
				return new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
			}
		}
		
		ResponseEntity<UtenteVo> response = null;
		UtenteVo updateVo = service.expireByUsername(username, expireDate);
		if(updateVo!=null) {
			response = new ResponseEntity<UtenteVo>(updateVo, HttpStatus.OK);
		} else {
			response = new ResponseEntity<UtenteVo>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
