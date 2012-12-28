package pt.ist.bennubone.coffee.domain;

public class CoffeeOrderEntry extends CoffeeOrderEntry_Base {

    public CoffeeOrderEntry(CoffeeItem item, int quantity) {
	setItem(item);
	setQuantity(quantity);
    }

    public void delete() {
	setCoffeeOrder(null);
	setItem(null);
	deleteDomainObject();
    }

}
