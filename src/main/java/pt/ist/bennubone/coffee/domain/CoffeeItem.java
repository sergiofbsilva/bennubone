package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

public class CoffeeItem extends CoffeeItem_Base {
    
    public CoffeeItem(String name, BigDecimal unitValue) {
    	this(name, unitValue, 10);
    }
    
    public CoffeeItem(String name, BigDecimal unitValue, int numUnits) {
    	setName(name);
    	setUnitValue(unitValue);
    	setNumUnits(numUnits);
    	setAvailable(true);
    }
    
}
