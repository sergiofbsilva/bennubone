package pt.ist.bennubone.coffee.util;

import pt.ist.bennubone.coffee.domain.CoffeeManager;
import pt.ist.fenixframework.Config;
import pt.ist.fenixWebFramework.FenixWebFramework;

public class Bootstrap {

    public static void run() {
        try {
            //FenixWebFramework.bootStrap();
            //PropertiesManager.getFenixFrameworkConfig();
        } catch(Error e) {
                System.err.println("SOMETHINGS WRONG: "+e);
        }
    }
}