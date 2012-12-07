package pt.ist.bennubone.coffee.util;

import pt.ist.fenixWebFramework.FenixWebFramework;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class Bootstrap extends ServletContainer {

    static {
	System.out.println("Initializing Bennubone.");
	FenixWebFramework.bootStrap(PropertiesManager.getFenixFrameworkConfig());
    }

}