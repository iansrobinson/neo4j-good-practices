package org.neo4j.good_practices;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;

public class ExampleGraph
{
    public static void populate( GraphDatabaseService db )
    {
        ExecutionEngine engine = new ExecutionEngine( db );

        String cypher = "CREATE (sue:person {name:'Sue'}),\n" +
                "       (bill:person {name:'Bill'}),\n" +
                "       (lucy:person {name:'Lucy'}),\n" +
                "       (acme:company {name:'Acme'}),\n" +
                "       (java:skill {name:'Java'}),\n" +
                "       (csharp:skill {name:'C#'}),\n" +
                "       (neo4j:skill {name:'Neo4j'}),\n" +
                "       (ruby:skill {name:'Ruby'}),\n" +
                "       (sue)-[:WORKS_FOR]->(acme),\n" +
                "       (bill)-[:WORKS_FOR]->(acme),\n" +
                "       (lucy)-[:WORKS_FOR]->(acme),\n" +
                "       (sue)-[:HAS_SKILL]->(java),\n" +
                "       (sue)-[:HAS_SKILL]->(csharp),\n" +
                "       (sue)-[:HAS_SKILL]->(neo4j),\n" +
                "       (bill)-[:HAS_SKILL]->(neo4j),\n" +
                "       (bill)-[:HAS_SKILL]->(ruby),\n" +
                "       (lucy)-[:HAS_SKILL]->(java),\n" +
                "       (lucy)-[:HAS_SKILL]->(neo4j)";


        engine.execute( cypher );
    }
}
