package org.neo4j.good_practices;

import java.util.Collections;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class DatabaseFixture
{
    public static DatabaseFixtureBuilder createDatabase()
    {
        return new DatabaseFixtureBuilder( new TestGraphDatabaseFactory()
                .newImpermanentDatabaseBuilder()
                .newGraphDatabase() );
    }

    public static DatabaseFixtureBuilder useExistingDatabase( GraphDatabaseService db )
    {
        return new DatabaseFixtureBuilder( db );
    }

    private final GraphDatabaseService db;
    private final ExecutionEngine executionEngine;

    private DatabaseFixture( GraphDatabaseService db, String initialContents, Iterable<Migration> migrations )
    {
        this.db = db;
        this.executionEngine = new ExecutionEngine( db );

        populateWith( initialContents );
        applyMigrations( migrations );
    }

    public GraphDatabaseService database()
    {
        return db;
    }

    public ExecutionEngine executionEngine()
    {
        return executionEngine;
    }

    public void shutdown()
    {
        db.shutdown();
    }

    private ExecutionResult populateWith( String cypher )
    {
        return executionEngine.execute( cypher );
    }

    private void applyMigrations( Iterable<Migration> migrations )
    {
        for ( Migration migration : migrations )
        {
            migration.apply( db );
        }
    }

    public static class DatabaseFixtureBuilder
    {
        private final GraphDatabaseService db;
        private String initialContents;

        private DatabaseFixtureBuilder( GraphDatabaseService db )
        {
            this.db = db;
        }

        public DatabaseFixtureBuilder populateWith( String cypher )
        {
            initialContents = cypher;
            return this;
        }

        public DatabaseFixture applyMigrations( Iterable<Migration> migrations )
        {
            return new DatabaseFixture( db, initialContents, migrations );
        }

        public DatabaseFixture noMigrations()
        {
            return new DatabaseFixture( db, initialContents, Collections.<Migration>emptyList() );
        }
    }
}
