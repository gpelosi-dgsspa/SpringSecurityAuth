package com.cex.application.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.DatatypeConverter;

public class CryptoUtil 
{
	private static final Logger log = LoggerFactory.getLogger(CryptoUtil.class);
	
	private static final String ENCRYPTION_ALGORITHM = "MD5";
	private static final String CHARSET_FORMAT = "UTF8";
	
	public static String encodeToSha256(String str) {
		return DigestUtils.sha256Hex(str);
	}
	
	public static String encodeToMD5Base64(String str) 
	{
		String encodedString = null;
		try 
		{
			MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			md.update(str.getBytes());
			byte[] digest = md.digest();
			encodedString = Base64.getEncoder().encodeToString(digest);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return encodedString;
	}
	
	public static String encodeToMD5(String str) 
	{
		String encodedString = null;
		try 
		{
			MessageDigest md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			md.update(str.getBytes());
			byte[] digest = md.digest();
			encodedString = digest.toString();
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return encodedString;
	}
	
	public static String encodeToBase64(String str)
	{
		String encodedString = null;
		encodedString = Base64.getEncoder().encodeToString(str.getBytes());
		return encodedString;
	}
	
	public static String encodeToMD5Hash(String str) 
	{
		String encodedString = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			messageDigest.update(str.getBytes(Charset.forName(CHARSET_FORMAT)));
			byte[] digest = messageDigest.digest();
			encodedString = new String(Hex.encodeHex(digest));
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return encodedString;
	}
	
	public static String encodeToMD5HashBase64(String str) 
	{
		String encodedString = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
			messageDigest.update(str.getBytes(Charset.forName(CHARSET_FORMAT)));
			byte[] digest = messageDigest.digest();
			String result = new String(Hex.encodeHex(digest));
			encodedString = Base64.getEncoder().encodeToString(result.getBytes());
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return encodedString;
	}
	
	/**
	 * Restituisce l'hash di un file
	 */
	public static String getMD5HashFromFile(String pathFile) 
	{
		String hash = null;
		try {
			byte[] fileBytes = Files.readAllBytes(Paths.get(pathFile));
			byte[] hashBytes = MessageDigest.getInstance("MD5").digest(fileBytes);
			hash = new String(Hex.encodeHex(hashBytes));
		} catch (IOException | NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
		return hash;
	}
	
	/**
	 * Come il metodo getMD5HashFromFile ma il risultato � in upper case
	 */
	public static String getMD5ChecksumFromFile(String pathFile) 
	{
		String hash = null;
		try {
			byte[] fileBytes = Files.readAllBytes(Paths.get(pathFile));
			byte[] hashBytes = MessageDigest.getInstance("MD5").digest(fileBytes);
			hash = DatatypeConverter.printHexBinary(hashBytes);
		} catch (NoSuchAlgorithmException | IOException e) {
			log.error(e.getMessage());
		}
		return hash;
	}
	
	public static String getMD5HashFromFile(InputStream is) 
	{
		String hash = null;
		try {
			hash = DigestUtils.md5Hex(is);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return hash;
	}

	public static String decodeFromBase64(String base64) 
	{
		byte[] decodedBytes = Base64.getDecoder().decode(base64);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}

}
