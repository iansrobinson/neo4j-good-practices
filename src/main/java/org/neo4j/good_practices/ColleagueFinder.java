package org.neo4j.good_practices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
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

        ExecutionResult result = cypherEngine.execute( cypher, params );
        System.out.println( result.dumpToString() );

        return cypherEngine.execute( cypher, params ).iterator();
    }

    public Iterator<Map<String, Object>> findWithMatchingSkills( String name, String... skills )
    {
        String cypher = "MATCH p=(me:person)-[:WORKED_ON]->()-[:WORKED_ON*0..2]-()\n" +
                "        <-[:WORKED_ON]-(person)-[:HAS_SKILL]->(skill)\n" +
                "WHERE me.name = {name}\n" +
                "      AND person <> me \n" +
                "      AND skill.name IN {skills}\n" +
                "WITH person, skill, min(length(p)) as pathLength\n" +
                "RETURN person.name AS name,\n" +
                "       count(skill) AS score,\n" +
                "       collect(skill.name) AS skills,\n" +
                "       ((pathLength - 1)/2) AS distance\n" +
                "ORDER BY score DESC;";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "name", name );
        params.put( "skills", skills );

        ExecutionResult result = cypherEngine.execute( cypher, params );
        System.out.println( result.dumpToString() );

        return cypherEngine.execute( cypher, params ).iterator();
    }
}
