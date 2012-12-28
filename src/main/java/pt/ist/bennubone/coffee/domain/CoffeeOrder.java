package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import pt.ist.fenixWebFramework.services.Service;

public class CoffeeOrder extends CoffeeOrder_Base {

    public CoffeeOrder(User author) {
	setUser(author);
    }

    public BigDecimal calculateTotal() {
	BigDecimal total = new BigDecimal(0.0);
	for (CoffeeOrderEntry coffeeOrderEntry : getEntrySet()) {
	    CoffeeItem coffeeItem = coffeeOrderEntry.getItem();
	    total.add(coffeeItem.calculateTotalPrice());
	}
	return total;
    }

    public int getTotalCount() {
	int totalCount = 0;
	for (CoffeeOrderEntry entry : getEntrySet()) {
	    totalCount += entry.getQuantity() * entry.getItem().getNumUnits();
	}
	return totalCount;
    }

    public CoffeeOrderEntry getEntry(CoffeeItem item) {
	for (CoffeeOrderEntry entry : getEntry()) {
	    if (entry.getItem().equals(item)) {
		return entry;
	    }
	}
	return null;
    }

    @Service
    public void addEntry(CoffeeItem item, int quantity) {
	final CoffeeOrderEntry entry = getEntry(item);
	setEntry(item, entry != null ? entry.getQuantity() + quantity : quantity);
    }

    private void setEntry(CoffeeItem item, int quantity) {
	final CoffeeOrderEntry entry = getEntry(item);
	if (entry != null) {
	    entry.setQuantity(quantity);
	} else {
	    addEntry(new CoffeeOrderEntry(item, quantity));
	}
    }

    public void delete() {
	setUser(null);
	setCoffeeBatch(null);
	getEntry().clear();
	deleteDomainObject();
    }

}
