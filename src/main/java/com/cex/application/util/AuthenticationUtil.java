package com.cex.application.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.cex.application.config.properties.ConfigProperties;
import com.cex.application.entity.authentication.Gerarchia;
import com.cex.application.repository.authentication.GerarchiaRepository;

public class AuthenticationUtil 
{
	private static final Logger log = LoggerFactory.getLogger(AuthenticationUtil.class);
	
	private static final String TEMPLATES = "/templates";
	private static final String[] EXCLUDED = {"fragments"};
	
	private static final String SRC_CONFIG = "CONFIG";
	private static final String SRC_DB = "DB";
	private static final String ROLE_PREFIX = "ROLE_";
	private static final String ROLE_AUTH_TYPE = "ROLE";
	
	/**
	 * Usare questo metodo se le pagine si trovano tutte nella cartella "templates" (root)
	 * @param webPageSuffix
	 * @return
	 */
	public static List<String> getRootApplicationPages(String webPageSuffix)
	{
		List<String> listaPagine = new ArrayList<String>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resourcePatternResolver.getResources(TEMPLATES+"/**"+webPageSuffix);
			for(Resource r: resources) {
				String page = r.getFilename();
				String pageName = page.substring(0, page.lastIndexOf(webPageSuffix));
				listaPagine.add(pageName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPagine;
	}
	
	/**
	 * Usare questo metodo quando le pagine (template) applicative si trovano dentro cartelle annidate in "templates" (root)
	 * @param webPageSuffix
	 * @return
	 */
	public static List<String> getAllApplicationPages(String webPageSuffix)
	{
		List<String> listaPagine = new ArrayList<String>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resourcePatternResolver.getResources(TEMPLATES+"/**");
			for(Resource r: resources) 
			{
				String page = r.getURL().toString();
				page = page.substring(page.toString().indexOf(TEMPLATES)+TEMPLATES.length()+1);
				if(page.endsWith(webPageSuffix)) 
				{
					page = page.substring(0, page.lastIndexOf(webPageSuffix));
					if(isTemplateIncluded(page)) {
						listaPagine.add(page);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaPagine;
	}
	
	/**
	 * Verifica se il template è da includere o meno all'interno della configurazione applicativa
	 * @param template
	 * @return
	 */
	private static boolean isTemplateIncluded(String template) {
		boolean included = true;
		for(String escluso: EXCLUDED) {
			if(template.startsWith(escluso+"/")) {
				included = false;
				break;
			}
		}
		return included;
	}
	
	public static String getHierarchySource(ApplicationContext applicationContext) 
	{
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		String hierSource = cp.getHierarchiesSource();
		return hierSource;
	}
	
	public static String[] readHierarchyList(ApplicationContext applicationContext, GerarchiaRepository hierRepo) 
	{
		String[] gerarchie = null;
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		String hierSource = cp.getHierarchiesSource();
		if(hierSource.equals(SRC_CONFIG)) {
			gerarchie = readConfigHierarchyList(applicationContext);
			log.info("GERARCHIE applicative acquisite da File di properties");
		} else if(hierSource.equals(SRC_DB)) {
			gerarchie = readDBHierarchyList(hierRepo);
			log.info("GERARCHIE applicative acquisite da Database");
		}
		
		return gerarchie;
	}
	
	public static String[] readDBHierarchyList(GerarchiaRepository hierRepo) 
	{
		List<Gerarchia> lista = hierRepo.findAll();
		List<String> listaGerarchie = new ArrayList<String>();
		for(Gerarchia g: lista) 
		{
			String strGerarchia = null;
			if(g.getAuthorityType().equals(ROLE_AUTH_TYPE)) {
				strGerarchia = ROLE_PREFIX + g.getParentAuth() + " > " + ROLE_PREFIX + g.getChildAuth();
			} else {
				strGerarchia = g.getParentAuth() + " > " + g.getChildAuth();
			}
			listaGerarchie.add(strGerarchia);
		}
		
		return listaGerarchie.toArray(String[]::new);
	}
	
	public static String[] readConfigHierarchyList(ApplicationContext applicationContext) 
	{
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		List<String> hierarchies = new ArrayList<String>();
		
		String roles = cp.getRolesHierarchies();
		String[] rolesHier = roles.split(",");
		for(String s: rolesHier) {
			s = s.trim();
			if(!s.equals("")) {
				hierarchies.add(s);
			}
		}
		
		String authorities = cp.getAuthoritiesHierarchies();
		String[] authHier = authorities.split(",");
		for(String s: authHier) {
			s = s.trim();
			if(!s.equals("")) {
				hierarchies.add(s);
			}
		}
		
		String[] hierarchyList = hierarchies.toArray(String[]::new);
		return hierarchyList;
	}
	
	public static String[] readRoleHierarchyList(ApplicationContext applicationContext, GerarchiaRepository hierRepo) 
	{
		String[] hierarchyList = null;
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		String hierSource = cp.getHierarchiesSource();
		if(hierSource.equals(SRC_CONFIG)) 
		{
			List<String> hierarchies = new ArrayList<String>();
			String roles = cp.getRolesHierarchies();
			String[] rolesHier = roles.split(",");
			for(String s: rolesHier) {
				s = s.trim();
				if(!s.equals("")) {
					hierarchies.add(s);
				}
			}
			hierarchyList = hierarchies.toArray(String[]::new);
		} 
		else if(hierSource.equals(SRC_DB)) 
		{
			ArrayList<String> listaFinale = new ArrayList<String>();
			String[] lista = readDBHierarchyList(hierRepo);
			for(String s: lista) {
				if(s.contains(ROLE_PREFIX)) {
					listaFinale.add(s);
				}
			}
			hierarchyList = listaFinale.toArray(String[]::new);
		}
		return hierarchyList;
	}
	
	public static String[] readAuthoritiesHierarchyList(ApplicationContext applicationContext, GerarchiaRepository hierRepo) 
	{
		String[] hierarchyList = null;
		ConfigProperties cp = applicationContext.getBean(ConfigProperties.class);
		String hierSource = cp.getHierarchiesSource();
		if(hierSource.equals(SRC_CONFIG))
		{
			List<String> hierarchies = new ArrayList<String>();
			String authorities = cp.getAuthoritiesHierarchies();
			String[] authHier = authorities.split(",");
			for(String s: authHier) {
				s = s.trim();
				if(!s.equals("")) {
					hierarchies.add(s);
				}
			}
			hierarchyList = hierarchies.toArray(String[]::new);
		}
		else if(hierSource.equals(SRC_DB)) 
		{
			ArrayList<String> listaFinale = new ArrayList<String>();
			String[] lista = readDBHierarchyList(hierRepo);
			for(String s: lista) {
				if(!s.contains(ROLE_PREFIX)) {
					listaFinale.add(s);
				}
			}
			hierarchyList = listaFinale.toArray(String[]::new);
		}
		return hierarchyList;
	}
}
