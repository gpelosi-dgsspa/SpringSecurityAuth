package com.cex.application.config.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cex.application.entity.authentication.UserGrant;
import com.cex.application.entity.authentication.Utente;
import com.cex.application.entity.authentication.UtenteRuolo;
import com.cex.application.entity.authentication.id.UtenteRuoloId;
import com.cex.application.repository.authentication.UserGrantRepository;
import com.cex.application.repository.authentication.UtenteRepository;
import com.cex.application.repository.authentication.UtenteRuoloRepository;

public class AuthUserDetailsService implements UserDetailsService {
	
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private UtenteRepository utenteRepo;
	
	@Autowired
    private UserGrantRepository userGrantRepo;
	
	@Autowired
    private UtenteRuoloRepository utenteRuoloRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
    	Utente user = getUtente(username);
    	
		AuthUserDetails logonUserDetails = new AuthUserDetails(user);
		ArrayList<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
		logonUserDetails.setAuthorities(grantedAuthorities);
    	return logonUserDetails;
    }
	
    private ArrayList<GrantedAuthority> getGrantedAuthorities(Utente user)
    {
    	ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		List<UserGrant> listaGrants = userGrantRepo.findByUsername(user.getUsername());
		if(listaGrants!=null && !listaGrants.isEmpty()) {
			for(UserGrant grant: listaGrants) {
				grantedAuthorities.add(new SimpleGrantedAuthority(grant.getIdPermesso()));
			}
		}
		
		List<UtenteRuolo> listaRuoli = getRoles(user.getId());
		if(listaRuoli!=null && !listaRuoli.isEmpty()) {
			for(UtenteRuolo ur: listaRuoli) {
				grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + ur.getId().getIdRuolo()));
			}
		}
		
		return grantedAuthorities;
    }
    
    private List<UtenteRuolo> getRoles(Long idUtente)
    {
    	UtenteRuolo entity = new UtenteRuolo();
		UtenteRuoloId entityId = new UtenteRuoloId();
		entityId.setIdUtente(idUtente);
		entity.setId(entityId);
		Example<UtenteRuolo> example = Example.of(entity);
		List<UtenteRuolo> lista = utenteRuoloRepo.findAll(example);
		return lista;
    }
    
    private Utente getUtente(String username) {
    	Utente utente = utenteRepo.findByUsername(username);
        if (utente==null) {
            throw new UsernameNotFoundException("User not found");
        }
    	return utente;
    }
    
//    private Boolean getExpired(Date expirationDate) 
//    {
//		if(expirationDate==null) {
//			return Boolean.valueOf(false);
//		} else {
//			int compare = expirationDate.compareTo(new Date());
//			if(compare >= 0) {
//				return true;
//			} else {
//				return false;
//			}
//		}
//	}
    
}
