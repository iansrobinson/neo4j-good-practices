package org.neo4j.good_practices;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

@Provider
public class PrinterProvider extends SingletonTypeInjectableProvider<Context, Printer>
{
    public PrinterProvider()
    {
        super( Printer.class, new SystemPrinter() );
    }
}
