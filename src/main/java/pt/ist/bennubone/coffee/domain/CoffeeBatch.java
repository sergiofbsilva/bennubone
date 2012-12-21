package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class CoffeeBatch extends CoffeeBatch_Base {
    
    public CoffeeBatch() {
    	this.setCreationTimestamp(new DateTime());
    }
    
    @Override
    public BigDecimal getShippingCharges() {
    	if(super.getShippingCharges() != null) {
    		return super.getShippingCharges();
    	} else {
    		int totalCount = getTotalCount();
    		if(totalCount > 200) {
    			return BigDecimal.ZERO;
    		} else {
    			return new BigDecimal(3.00);
    		}
    	}
    }
    
    public int getTotalCount() {
    	int totalCount = 0;
    	for(CoffeeOrder coffeeOrder : getCoffeeOrderSet()) {
    		totalCount += coffeeOrder.getTotalCount();
    	}
    	return totalCount;
    }
    
    public BigDecimal calculateTotal() {
    	BigDecimal total = new BigDecimal(0.0);
    	for(CoffeeOrder coffeeOrder : getCoffeeOrderSet()) {
    		total.add(coffeeOrder.calculateTotal());
    	}
    	return total;
    }
    
}
