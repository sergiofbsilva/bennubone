package pt.ist.bennubone.coffee.domain;

import org.json.simple.JSONObject;

public class User extends User_Base {

    public User() {
	super();
    }

    public void delete() {
	setCoffeeManager(null);
	super.deleteDomainObject();
    }

    public JSONObject json() {
	JSONObject obj = new JSONObject();
	obj.put("username", getUsername());
	obj.put("avatar", getAvatar());
	return obj;
    }

}
