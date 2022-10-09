package step.learning.services;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class StringService {

    @Inject
    //private CharService charService;  // CharService - implementation
    private SymbolService charService;  // SymbolService - interface

    @Inject
    @Named("max")
    private RandomProvider randomProvider;

    public String getString() {
        return String.format("Hello, %c, world %d times!",
                charService.getChar(), randomProvider.getN());
    }
}
