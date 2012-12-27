package pt.ist.bennubone.coffee.domain;

import java.math.BigDecimal;

import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.FFDomainException;

public class CoffeeItem extends CoffeeItem_Base {

    public static class DuplicateCoffeeItemException extends FFDomainException {

	public DuplicateCoffeeItemException(String message) {
	    super(message);
	}

	private static final long serialVersionUID = 1L;

    }

    public CoffeeItem(String name, BigDecimal unitValue) {
	this(name, unitValue, 10);
    }

    public CoffeeItem(String name, BigDecimal unitValue, int numUnits) {
	setName(name);
	setUnitValue(unitValue);
	setNumUnits(numUnits);
	setAvailable(true);
	setCoffeeManager(CoffeeManager.getInstance());
    }

    @Override
    public void setName(String name) {
	if (CoffeeManager.getInstance().hasCoffeeItem(name)) {
	    throw new DuplicateCoffeeItemException(String.format("An item with the name %s already exists.", name));
	}
	super.setName(name.trim());
    }

    public BigDecimal calculateTotalPrice() {
	return getUnitValue().multiply(new BigDecimal(getNumUnits()));
    }

    @Service
    public void delete() {
	setCoffeeManager(null);
	deleteDomainObject();
    }

}
