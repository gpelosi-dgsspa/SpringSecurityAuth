package com.cex.application.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.cex.application.config.properties.ConfigProperties;
import com.cex.application.service.UploadService;

@Service
@PropertySource("classpath:config.properties")
public class UploadServiceImpl implements UploadService
{
	private static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public boolean uploadFileBase64(String base64, String fileName) 
	{
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		boolean esito = false;
		try {
//			String fileType = base64.substring(0,base64.indexOf(","));
//			log.info("Caricamento in corso di: [" + fileType + "]");
			String base64Content = base64.substring(base64.indexOf(",")+1);
			byte[] data = Base64.getDecoder().decode(base64Content);
			String filePath = cp.getDestinationPath() + "/" + new Date().getTime() + "-" + fileName;
			OutputStream stream = new FileOutputStream(filePath);
			stream.write(data);
			stream.flush();
			stream.close();
			esito = true;
		} catch (Exception e) {
			log.error("Errore nel caricamento del file");
		}
		return esito;
	}
	
}
