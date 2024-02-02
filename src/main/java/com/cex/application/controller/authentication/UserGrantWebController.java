package com.cex.application.controller.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cex.application.repository.authentication.GerarchiaRepository;
import com.cex.application.service.authentication.UtentePermessoService;
import com.cex.application.service.authentication.UtenteRuoloService;
import com.cex.application.util.AuthenticationUtil;
import com.cex.application.vo.authentication.UserGrantWebVo;
import com.cex.application.vo.authentication.UtentePermessoVo;
import com.cex.application.vo.authentication.UtenteRuoloVo;
import com.cex.application.vo.authentication.common.MessageVo;

@RestController
@RequestMapping("/user-grant-web")
public class UserGrantWebController 
{
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UtenteRuoloService utenteRuoloService;
	
	@Autowired
	private UtentePermessoService utentePermessoService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private GerarchiaRepository hierRepo;
	
	@PostMapping("/update-grants/{id}")
	public ResponseEntity<MessageVo> updateGrantsByUserId(@PathVariable(required = false) Long id, @RequestBody List<UserGrantWebVo> listaVo)
	{
		ResponseEntity<MessageVo> response = null;
		
		try	{
			Long userId = id;
			if(userId!=null) {
				utenteRuoloService.deleteByUserId(userId);
				utentePermessoService.deleteByUserId(userId);
			}
			
			List<UtenteRuoloVo> listaRuoli = new ArrayList<UtenteRuoloVo>();
			List<UtentePermessoVo> listaPermessi = new ArrayList<UtentePermessoVo>();
			for(UserGrantWebVo vo: listaVo) 
			{
				if(vo.getIdRuolo()!=null && !vo.getIdRuolo().equals("")) {
					UtenteRuoloVo urVo = new UtenteRuoloVo();
					urVo.setIdUtente(vo.getIdUtente());
					urVo.setIdRuolo(vo.getIdRuolo());
					listaRuoli.add(urVo);
				} else {
					UtentePermessoVo upVo = new UtentePermessoVo();
					upVo.setIdUtente(vo.getIdUtente());
					upVo.setIdPermesso(vo.getIdPermesso());
					listaPermessi.add(upVo);
				}
			}
			
			for(UtenteRuoloVo ur: listaRuoli) {
				utenteRuoloService.add(ur);
			}
			
			for(UtentePermessoVo up: listaPermessi) {
				utentePermessoService.add(up);
			}
		
			MessageVo message = new MessageVo();
			message.setCode(Integer.valueOf(200));
			message.setMessage("Ruoli e Permessi aggiunti");
			response = new ResponseEntity<MessageVo>(message, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<MessageVo>(HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
	@GetMapping("/get-roles-hierarchies")
	public ResponseEntity<String[]> getRolesHierarchies(){
		String[] gerarchieRuoli = AuthenticationUtil.readRoleHierarchyList(applicationContext, hierRepo);
		ResponseEntity<String[]> response = new ResponseEntity<String[]>(gerarchieRuoli, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get-authorities-hierarchies")
	public ResponseEntity<String[]> getAuthoritiesHierarchies(){
		String[] gerarchiePermessi = AuthenticationUtil.readAuthoritiesHierarchyList(applicationContext, hierRepo);
		ResponseEntity<String[]> response = new ResponseEntity<String[]>(gerarchiePermessi, HttpStatus.OK);
		return response;
	}
	
}
