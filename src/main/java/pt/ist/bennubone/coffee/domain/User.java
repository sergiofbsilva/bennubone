package pt.ist.bennubone.coffee.domain;

public class User extends User_Base {

	public User(String firstName, String lastName, String username, String email) {
		setFirstName(firstName);
		setLastName(lastName);
		setUsername(username);
		setEmail(email);
	}

}
