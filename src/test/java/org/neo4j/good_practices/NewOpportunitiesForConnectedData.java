package org.neo4j.good_practices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.Iterator;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class NewOpportunitiesForConnectedData
{
    private static GraphDatabaseService db;
    private static ColleagueFinder finder;

    @BeforeClass
    public static void init()
    {
        db = new TestGraphDatabaseFactory().newImpermanentDatabase();
        ExampleGraph.populateWithLargeGraph( db );

        finder = new ColleagueFinder( db );
    }

    @AfterClass
    public static void shutdown()
    {
        db.shutdown();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFindColleaguesWithSimilarSkills() throws Exception
    {
        // when
        Iterator<Map<String, Object>> results = finder.findFor( "Ian" );

        // then
        Map<String, Object> row = results.next();
        assertEquals( "Ben", row.get( "name" ) );
        assertThat( (Iterable<String>) row.get( "skills" ), hasItems( "Neo4j", "REST" ) );

        row = results.next();
        assertEquals( "Charlie", row.get( "name" ) );
        assertThat( (Iterable<String>) row.get( "skills" ), hasItems( "Neo4j" ) );

        assertFalse( results.hasNext() );
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFindPeopleWithMatchingSkills() throws Exception
    {
        // when
        Iterator<Map<String, Object>> results = finder.findWithMatchingSkills( "Ian", "Java", "Clojure", "SQL" );

        // then
        Map<String, Object> row = results.next();
        assertEquals( "Arnold", row.get( "name" ) );
        assertEquals( 2L, row.get( "score" ) );
        assertEquals( 2L, row.get( "distance" ) );
        assertThat( (Iterable<String>) row.get( "skills" ), hasItems( "Clojure", "Java" ) );

        row = results.next();
        assertEquals( "Charlie", row.get( "name" ) );
        assertEquals( 1L, row.get( "score" ) );
        assertEquals( 1L, row.get( "distance" ) );
        assertThat( (Iterable<String>) row.get( "skills" ), hasItems( "SQL" ) );

        assertFalse( results.hasNext() );
    }
}
