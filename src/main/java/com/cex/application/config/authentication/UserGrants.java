package com.cex.application.config.authentication;

public class UserGrants 
{
	public static final String P_ADMIN = "ADMIN";
	public static final String P_WRITE = "WRITE";
	public static final String P_READ = "READ";
	public static final String P_DELETE = "DELETE";
	
	public static final String R_ADMINISTRATOR = "ADMINISTRATOR";
	public static final String R_SUPER_USER = "SUPER_USER";
	public static final String R_USER = "USER";
	
	public static enum Auth {
		ADMIN,
		WRITE,
		READ,
		DELETE
	};
	
	public static enum Role {
		ADMINISTRATOR,
		SUPER_USER,
		USER
	};
}
