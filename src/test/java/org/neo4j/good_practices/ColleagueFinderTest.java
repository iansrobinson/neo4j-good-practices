package org.neo4j.good_practices;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.neo4j.graphdb.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ColleagueFinderTest
{
    private DatabaseFixture dbFixture;
    private ColleagueFinder finder;

    @Before
    public void init()
    {
        dbFixture = DatabaseFixture.createDatabase()
                .populateWith( ExampleData.smallGraph )
                .applyMigrations( Collections.<Migration>emptyList() );

        finder = new ColleagueFinder( dbFixture.database() );
    }

    @After
    public void shutdown()
    {
        dbFixture.shutdown();
    }

    @Test
    public void shouldFindColleaguesWithSimilarSkills() throws Exception
    {
        // when
        Result result = finder.findColleaguesFor( "Ian" );

        // then
        assertEquals( "Lucy", result.next().get( "name" ) );
        assertEquals( "Bill", result.next().get( "name" ) );

        assertFalse( result.hasNext() );
    }
}
