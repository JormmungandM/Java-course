package step.learning.services;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import step.learning.services.hash.HashService;
import step.learning.services.hash.MD5HashService;
import step.learning.services.hash.Sha1HashService;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(SymbolService.class).to(CharService.class);
        bind(HashService.class).annotatedWith(Names.named("128")).to(MD5HashService.class);
        bind(HashService.class).annotatedWith(Names.named("160")).to(Sha1HashService.class);

        bind(String.class)
                .annotatedWith(Names.named("MsConnectionString"))
                .toInstance("MsConnectionString");
        bind(String.class)
                .annotatedWith(Names.named("OracleConnectionString"))
                .toInstance("OracleConnectionString");

        bind(DataTimeService.class).to(DataTime.class); // binding

    }

    @Provides  @Named("max")
    RandomProvider getRandomProviderMax(){
        return new RandomProviderMax();
    }
    @Provides
    @Named("ten")
    RandomProvider getRandomProviderTen(){
        return new RandomProviderTen();
    }

}
