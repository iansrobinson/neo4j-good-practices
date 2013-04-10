package org.neo4j.good_practices;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;

public class ExampleGraph
{
    public static void populate( GraphDatabaseService db )
    {
        ExecutionEngine engine = new ExecutionEngine( db );

        String cypher = "CREATE sue:person VALUES {name:'Sue'},\n" +
                "       bill:person VALUES {name:'Bill'},\n" +
                "       lucy:person VALUES {name:'Lucy'},\n" +
                "       acme:company VALUES {name:'Acme'},\n" +
                "       java:skill VALUES {name:'Java'},\n" +
                "       csharp:skill VALUES {name:'C#'},\n" +
                "       neo4j:skill VALUES {name:'Neo4j'},\n" +
                "       ruby:skill VALUES {name:'Ruby'},\n" +
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
