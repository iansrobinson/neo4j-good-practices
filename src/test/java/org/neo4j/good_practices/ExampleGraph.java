package org.neo4j.good_practices;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.graphdb.GraphDatabaseService;

public class ExampleGraph
{
    public static void populateWithSmallGraph( GraphDatabaseService db )
    {
        ExecutionEngine engine = new ExecutionEngine( db );

        String cypher = "CREATE (ian:person {name:'Ian'}),\n" +
                "       (bill:person {name:'Bill'}),\n" +
                "       (lucy:person {name:'Lucy'}),\n" +
                "       (acme:company {name:'Acme'}),\n" +
                "       (java:skill {name:'Java'}),\n" +
                "       (csharp:skill {name:'C#'}),\n" +
                "       (neo4j:skill {name:'Neo4j'}),\n" +
                "       (ruby:skill {name:'Ruby'}),\n" +
                "       (ian)-[:WORKS_FOR]->(acme),\n" +
                "       (bill)-[:WORKS_FOR]->(acme),\n" +
                "       (lucy)-[:WORKS_FOR]->(acme),\n" +
                "       (ian)-[:HAS_SKILL]->(java),\n" +
                "       (ian)-[:HAS_SKILL]->(csharp),\n" +
                "       (ian)-[:HAS_SKILL]->(neo4j),\n" +
                "       (bill)-[:HAS_SKILL]->(neo4j),\n" +
                "       (bill)-[:HAS_SKILL]->(ruby),\n" +
                "       (lucy)-[:HAS_SKILL]->(java),\n" +
                "       (lucy)-[:HAS_SKILL]->(neo4j)";


        engine.execute( cypher );
    }

    public static void populateWithLargeGraph( GraphDatabaseService db )
    {
        ExecutionEngine engine = new ExecutionEngine( db );

        String cypher = "CREATE\n" +
                        "(ben:person {name:'Ben'}),\n" +
                        "(arnold:person {name:'Arnold'}),\n" +
                        "(charlie:person {name:'Charlie'}),\n" +
                        "(gordon:person {name:'Gordon'}),\n" +
                        "(lucy:person {name:'Lucy'}),\n" +
                        "(emily:person {name:'Emily'}),\n" +
                        "(ian:person {name:'Ian'}),\n" +
                        "(kate:person {name:'Kate'}),\n" +
                        "(acme:company {name:'Acme, Inc'}),\n" +
                        "(startup:company {name:'Startup, Ltd'}),\n" +
                        "(neo4j:skill {name:'Neo4j'}),\n" +  //graphs
                        "(rest:skill {name:'REST'}),\n" +
                        "(dotNet:skill {name:'DotNet'}),\n" +  //art
                        "(ruby:skill {name:'Ruby'}),\n" +  //design
                        "(sql:skill {name:'SQL'}),\n" +    // medicine
                        "(architecture:skill {name:'Architecture'}),\n" + //drama
                        "(java:skill {name:'Java'}),\n" +
                        "(python:skill {name:'Python'}),\n" +    //music
                        "(javascript:skill {name:'Javascript'}),\n" +    //cars
                        "(clojure:skill {name:'Clojure'}),\n" +    //travel
                        "(phoenix:project {name:'Phoenix'}),\n" +
                        "(quantumLeap:project {name:'Quantum Leap'}),\n" +
                        "(nextGenPlatform:project {name:'Next Gen Platform'}),\n" +
                        "ben-[:WORKS_FOR]->acme,\n" +
                        "charlie-[:WORKS_FOR]->acme,\n" +
                        "lucy-[:WORKS_FOR]->acme,\n" +
                        "ian-[:WORKS_FOR]->acme,\n" +
                        "arnold-[:WORKS_FOR]->startup,\n" +
                        "gordon-[:WORKS_FOR]->startup,\n" +
                        "emily-[:WORKS_FOR]->startup,\n" +
                        "kate-[:WORKS_FOR]->startup,\n" +
                        "ben-[:HAS_SKILL]->neo4j,\n" +
                        "ben-[:HAS_SKILL]->rest,\n" +
                        "arnold-[:HAS_SKILL]->neo4j,\n" +
                        "arnold-[:HAS_SKILL]->java,\n" +
                        "arnold-[:HAS_SKILL]->rest,\n" +
                        "arnold-[:HAS_SKILL]->clojure,\n" +
                        "charlie-[:HAS_SKILL]->neo4j,\n" +
                        "charlie-[:HAS_SKILL]->javascript,\n" +
                        "charlie-[:HAS_SKILL]->sql,\n" +
                        "gordon-[:HAS_SKILL]->neo4j,\n" +
                        "gordon-[:HAS_SKILL]->dotNet,\n" +
                        "gordon-[:HAS_SKILL]->python,\n" +
                        "lucy-[:HAS_SKILL]->dotNet,\n" +
                        "lucy-[:HAS_SKILL]->architecture,\n" +
                        "lucy-[:HAS_SKILL]->python,\n" +
                        "emily-[:HAS_SKILL]->dotNet,\n" +
                        "emily-[:HAS_SKILL]->ruby,\n" +
                        "ian-[:HAS_SKILL]->java,\n" +
                        "ian-[:HAS_SKILL]->neo4j,\n" +
                        "ian-[:HAS_SKILL]->rest,\n" +
                        "kate-[:HAS_SKILL]->architecture,\n" +
                        "kate-[:HAS_SKILL]->python,\n" +
                        "arnold-[:WORKED_ON]->phoenix,\n" +
                        "kate-[:WORKED_ON]->phoenix,\n" +
                        "kate-[:WORKED_ON]->quantumLeap,\n" +
                        "emily-[:WORKED_ON]->quantumLeap,\n" +
                        "ben-[:WORKED_ON]->nextGenPlatform,\n" +
                        "emily-[:WORKED_ON]->nextGenPlatform,\n" +
                        "charlie-[:WORKED_ON]->nextGenPlatform,\n" +
                        "ian-[:WORKED_ON]->nextGenPlatform,\n" +
                        "ian-[:WORKED_ON]->quantumLeap";


        engine.execute( cypher );
    }
}
