package com.cex.application.config.authentication;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cex.application.entity.authentication.Utente;

public class AuthUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Utente user;
	private Collection<? extends GrantedAuthority> authorities;
	private Date accessDate;
    
    public AuthUserDetails(Utente user) {
        this.user = user;
        this.accessDate = new Date();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return authorities;
    }
    
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {
    	Date expirationDate = user.getExpirationDate();
    	boolean nonExpired = false;
    	if(expirationDate==null || expirationDate.after(new Date())) {
    		nonExpired = true;
    	}
        return nonExpired;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return !user.getLocked();
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }
 
    @Override
    public boolean isEnabled() {
        return user.getEnabled().booleanValue();
    }
     
    public String getFullName() {
        return user.getUsername();
    }

	public static String getUsername(Object o) {
		AuthUserDetails obj = (AuthUserDetails)o;
		return obj.user.getUsername();
	}
	
	public Date getAccessDate() {
		return accessDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthUserDetails other = (AuthUserDetails) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
