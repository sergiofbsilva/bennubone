package pt.ist.bennubone.coffee.util;

import pt.ist.fenixframework.FenixFramework;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Bootstrap extends ServletContainer {

    static {
	System.out.println("Initializing Bennubone.");
	FenixFramework.initialize(PropertiesManager.getFenixFrameworkConfig());
    }

}