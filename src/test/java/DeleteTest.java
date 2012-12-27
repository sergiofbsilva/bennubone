import java.math.BigDecimal;

import jvstm.TransactionalCommand;

import org.junit.Test;

import pt.ist.bennubone.coffee.domain.CoffeeItem;
import pt.ist.bennubone.coffee.util.PropertiesManager;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.ist.fenixframework.pstm.Transaction;

public class DeleteTest {

    private String externalId;

    @Test
    public void testDelete() {

	FenixFramework.initialize(PropertiesManager.getFenixFrameworkConfig());

	System.out.println("Create item ...");
	Transaction.withTransaction(false, new TransactionalCommand() {

	    @Override
	    public void doIt() {
		System.out.printf("tx %d \n", Transaction.current().getNumber());
		final CoffeeItem coffeeItem = new CoffeeItem("test", new BigDecimal("0.5"));
		externalId = coffeeItem.getExternalId();
	    }
	});

	System.out.printf("Delete item %s ...\n", externalId);

	Transaction.withTransaction(false, new TransactionalCommand() {

	    @Override
	    public void doIt() {
		System.out.printf("tx %d \n", Transaction.current().getNumber());
		CoffeeItem item = AbstractDomainObject.fromExternalId(externalId);
		item.delete();
	    }
	});

	System.out.printf("Get item's name %s ...\n", externalId);

	Transaction.withTransaction(true, new TransactionalCommand() {

	    @Override
	    public void doIt() {
		System.out.printf("tx %d \n", Transaction.current().getNumber());
		CoffeeItem item = AbstractDomainObject.fromExternalId(externalId);
		System.out.println("item name : " + item.getName());
	    }
	});
    }
}
