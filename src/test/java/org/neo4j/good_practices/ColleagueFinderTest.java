package org.neo4j.good_practices;

import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ColleagueFinderTest
{
    private GraphDatabaseService db;
    private ColleagueFinder finder;

    @Before
    public void init()
    {
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        ExampleGraph.populate( db );

        finder = new ColleagueFinder( new ExecutionEngine( db ) );
    }

    @After
    public void shutdown()
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
