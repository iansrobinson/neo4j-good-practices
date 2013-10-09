package org.neo4j.good_practices;

import org.neo4j.graphdb.GraphDatabaseService;

public interface Migration
{
    void apply(GraphDatabaseService db);
}
