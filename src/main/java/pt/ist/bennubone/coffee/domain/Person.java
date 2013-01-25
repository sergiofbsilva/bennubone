package pt.ist.bennubone.coffee.domain;

import java.nio.charset.Charset;

import com.google.common.hash.Hashing;

public class Person extends Person_Base {

	public Person(String name) {
		setName(name);
	}

	public String getAvatar() {
		String hash = Hashing.md5().hashString(getUser().getEmail(), Charset.forName("UTF-8")).toString();
		return "http://www.gravatar.com/avatar/" + hash;
	}

}
