package pt.ist.bennubone.coffee.domain;

import org.joda.time.DateTime;

public class CoffeeBatch extends CoffeeBatch_Base {
    
    public CoffeeBatch() {
    	this.setCreationTimestamp(new DateTime());
    }
}
