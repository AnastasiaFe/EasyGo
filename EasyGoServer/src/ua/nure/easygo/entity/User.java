package ua.nure.easygo.entity;

public class User implements Entity {
	public final int id;
	public final String login;
	public final String password;
	public final String email;
	public final String status;
	
	public User(int id, String login, String password, String email, String status){
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.status = status;
	}
	
	@Override
	public boolean equals(Object obj) {	
		return this.id == ((User)obj).id;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
}
