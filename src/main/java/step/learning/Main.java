package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.services.ConfigModule;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ConfigModule());
        App app = injector.getInstance( App.class );
        app.run();
    }
}
