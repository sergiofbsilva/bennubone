package pt.ist.bennubone.coffee.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CoffeeManagerUtils {

    public static String calculatePasswordHash(String password, String salt) {
	MessageDigest md = null;

	try {
	    md = MessageDigest.getInstance("SHA-1");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
	String value = password + "@" + salt;
	return new String(md.digest(value.getBytes()));
    }

    public static String generateSalt() {
	UUID uuid = UUID.randomUUID();
	return uuid.toString().replace("-", "");
    }

}
