package pt.ist.bennubone.coffee.domain;

import pt.ist.bennubone.coffee.util.CoffeeManagerUtils;

public class User extends User_Base {

    public User(String name, String email, String password) {
	setName(name);
	setEmail(email);
	String salt = CoffeeManagerUtils.generateSalt();
	String passwordHash = CoffeeManagerUtils.calculatePasswordHash(password, salt);
	setSalt(salt);
	setPasswordHash(passwordHash);
	setCoffeeManager(CoffeeManager.getInstance());
    }
}
