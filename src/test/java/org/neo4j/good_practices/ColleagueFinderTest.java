package org.neo4j.good_practices;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Iterator<Map<String, Object>> results = finder.findColleaguesFor( "Ian" );

        // then
        assertEquals( "Lucy", results.next().get( "name" ) );
        assertEquals( "Bill", results.next().get( "name" ) );

        assertFalse( results.hasNext() );
    }
}
