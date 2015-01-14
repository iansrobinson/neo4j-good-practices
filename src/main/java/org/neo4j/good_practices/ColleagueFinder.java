package org.neo4j.good_practices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;

public class ColleagueFinder
{
    private final GraphDatabaseService db;

    public ColleagueFinder( GraphDatabaseService db )
    {
        this.db = db;
    }

    public Iterator<Map<String, Object>> findColleaguesFor( String name )
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

        return db.execute( cypher, params );
    }

    public Iterator<Map<String, Object>> findPeopleFor( String name )
    {
        String cypher =
                "MATCH (me:person)-[:HAS_SKILL]->(skill),\n" +
                        "      (company)<-[:WORKS_FOR]-(person)-[:HAS_SKILL]->(skill)\n" +
                        "WHERE  me.name = {name}\n" +
                        "RETURN person.name AS name,\n" +
                        "       company.name AS company,\n" +
                        "       count(skill) AS score,\n" +
                        "       collect(skill.name) AS skills\n" +
                        "ORDER BY score DESC";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "name", name );

        return db.execute( cypher, params );
    }

    public Iterator<Map<String, Object>> findWithMatchingSkills( String name, String... skills )
    {
        String cypher = "MATCH p=(me:person)-[:WORKED_ON*2..4]-\n" +
                "        (person)-[:HAS_SKILL]->(skill)\n" +
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

        return db.execute( cypher, params );
    }
}
