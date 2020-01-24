package se.madev.main.model;

/**
 * Model that represents a user in the recruitment application.
 * @author MAD Developers
 * @version 1.0.0
 * @since 2020-01-22
 */
public class User {
	
	String username;
	String password;
	
	/**
	 * Creates a new instance of {@code User}.
	 * @param username The username that should belong to the new instance.
	 * @param password The password that should correspond to the username.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static User retrieveUser(String username) {
		//test
		return new User("martin", "tillberg");
	}
	
	@Override
	public String toString() {
		return "User{username:" + this.username + ", password:" + this.password + "}";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/**
	 * Get method for the attribute username.
	 * @return The username of the User.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set method for the attribute username.
	 * @param username The new username that the attribute username should obtain.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get method for the attribute password.
	 * @return The password of the User.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set method for the attribute password.
	 * @param password The new password that the attribute password should obtain. 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
