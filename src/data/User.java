package data;

import java.io.Serializable;

public class User implements Serializable {

	public final UserRoles role;
	public final String username;
	public final String password;

	public final String firstName;
	public final String lastName;

	User(String username, String password, String firstName, String lastName, UserRoles role){
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
	



}
