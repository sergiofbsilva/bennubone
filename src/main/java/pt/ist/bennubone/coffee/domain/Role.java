package pt.ist.bennubone.coffee.domain;

public class Role extends Role_Base {

	public Role(String name) {
		setName(name);
		setCoffeeManager(CoffeeManager.getInstance());
	}

}
