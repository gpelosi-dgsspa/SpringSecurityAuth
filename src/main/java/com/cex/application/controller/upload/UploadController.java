package com.cex.application.controller.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cex.application.service.UploadService;
import com.cex.application.vo.UploadFileVo;

@RestController
@RequestMapping("/upload")
public class UploadController 
{
//	private static final Logger log = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private UploadService service;
	
	@PostMapping("/base64")
	public ResponseEntity<String> uploadFileBase64(@RequestBody UploadFileVo vo)
	{
		if(vo==null || vo.getBase64Content()==null) {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		boolean esito = service.uploadFileBase64(vo.getBase64Content(), vo.getFileName());
		ResponseEntity<String> response;
		if(esito) {
			response = new ResponseEntity<String>("Caricamento effettuato", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
