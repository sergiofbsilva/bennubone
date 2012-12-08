package pt.ist.bennubone.coffee.domain;

import org.json.simple.JSONObject;

public class User extends User_Base {

    public User() {
	super();
    }
    
    public User(String firstName, String lastName, String username) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
    }

    public void delete() {
	setCoffeeManager(null);
	super.deleteDomainObject();
    }

    public JSONObject json() {
	JSONObject obj = new JSONObject();
	obj.put("firstName", getFirstName());
	obj.put("lastName", getLastName());
	obj.put("username", getUsername());
	return obj;
    }

}
