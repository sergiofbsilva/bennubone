package pt.ist.bennu.coffee.manager.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pt.ist.bennu.bennu.core.rest.serializer.JsonAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeBatchAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeItemAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeOrderAdapter;
import pt.ist.bennu.coffee.manager.adapter.CoffeeOrderEntrySerializer;
import pt.ist.bennu.coffee.manager.domain.CoffeeBatch;
import pt.ist.bennu.coffee.manager.domain.CoffeeItem;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrder;
import pt.ist.bennu.coffee.manager.domain.CoffeeOrderEntry;

@WebListener
public class BennuboneInitializer implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        JsonAdapter.registerTypeAdapter(CoffeeOrder.class, new CoffeeOrderAdapter());
        JsonAdapter.registerTypeAdapter(CoffeeItem.class, new CoffeeItemAdapter());
        JsonAdapter.registerTypeAdapter(CoffeeBatch.class, new CoffeeBatchAdapter());
        JsonAdapter.registerTypeAdapter(CoffeeOrderEntry.class, new CoffeeOrderEntrySerializer());
    }

}
