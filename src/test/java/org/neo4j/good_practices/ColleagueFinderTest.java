package org.neo4j.good_practices;

import java.util.Iterator;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ColleagueFinderTest
{
    private static GraphDatabaseService db;
    private static ColleagueFinder finder;

    @BeforeClass
    public static void init()
    {
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        ExampleGraph.populate( db );

        finder = new ColleagueFinder( new ExecutionEngine( db ) );
    }

    @AfterClass
    public static void shutdown()
    {
        db.shutdown();
    }

    @Test
    public void shouldFindColleaguesWithSimilarSkills() throws Exception
    {
        // when
        Iterator<Map<String, Object>> results = finder.findColleaguesFor( "Ian" );

        // then
        assertEquals( "Lucy", results.next().get( "name" ) );
        assertEquals( "Bill", results.next().get( "name" ) );

        assertFalse( results.hasNext() );
    }
}
