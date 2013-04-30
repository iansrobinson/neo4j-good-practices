package org.neo4j.good_practices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class ColleagueFinderTest
{
    private static GraphDatabaseService db;
    private static ColleagueFinder finder;

    @BeforeClass
    public static void init()
    {
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        ExampleGraph.populateWithSmallGraph( db );

        finder = new ColleagueFinder( db );
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
        Iterator<Map<String, Object>> results = finder.findFor( "Sue" );

        // then
        assertEquals( "Lucy", results.next().get( "name" ) );
        assertEquals( "Bill", results.next().get( "name" ) );

        assertFalse( results.hasNext() );
    }
}
