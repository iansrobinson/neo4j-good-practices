package org.neo4j.good_practices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;

public class ColleagueFinder
{
    private final ExecutionEngine cypherEngine;

    public ColleagueFinder( GraphDatabaseService db )
    {
        this.cypherEngine = new ExecutionEngine( db );
    }

    public Iterator<Map<String, Object>> findFor( String name )
    {
        String cypher =
                "MATCH (company)<-[:WORKS_FOR]-(me:person)-[:HAS_SKILL]->(skill),\n" +
                "      (company)<-[:WORKS_FOR]-(colleague)-[:HAS_SKILL]->(skill)\n" +
                "WHERE  me.name = {name}\n" +
                "RETURN colleague.name AS name,\n" +
                "       count(skill) AS score,\n" +
                "       collect(skill.name) AS skills\n" +
                "ORDER BY score DESC";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "name", name );

        return cypherEngine.execute( cypher, params ).iterator();
    }
}
