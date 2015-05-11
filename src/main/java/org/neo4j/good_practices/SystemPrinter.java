package org.neo4j.good_practices;

public class SystemPrinter implements Printer
{
    public void printLine( String value )
    {
        System.out.println( value );
    }
}
