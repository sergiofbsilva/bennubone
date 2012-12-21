package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

public class CoffeeOrder extends CoffeeOrder_Base {
    
    public CoffeeOrder(User author) {
    	setUser(author);
    }
    
    public BigDecimal calculateTotal() {
    	BigDecimal total = new BigDecimal(0.0);
    	for(CoffeeOrderEntry coffeeOrderEntry : getEntrySet()) {
    		CoffeeItem coffeeItem = coffeeOrderEntry.getItem();
    		total.add(coffeeItem.calculateTotalPrice());
    	}
    	return total;
    }

	public int getTotalCount() {
		int totalCount = 0;
		for(CoffeeOrderEntry entry : getEntrySet()) {
			totalCount += entry.getQuantity()*entry.getItem().getNumUnits();
		}
		return totalCount;
	}
    
}
