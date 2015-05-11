package org.neo4j.good_practices;

public class ExampleData
{
    public static String smallGraph = "CREATE (ian:Person {name:'Ian'}),\n" +
            "       (bill:Person {name:'Bill'}),\n" +
            "       (lucy:Person {name:'Lucy'}),\n" +
            "       (acme:Company {name:'Acme'}),\n" +
            "       (java:Skill {name:'Java'}),\n" +
            "       (csharp:Skill {name:'C#'}),\n" +
            "       (neo4j:Skill {name:'Neo4j'}),\n" +
            "       (ruby:Skill {name:'Ruby'}),\n" +
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

    public static String largeGraph = "CREATE\n" +
            "(ben:Person {name:'Ben'}),\n" +
            "(arnold:Person {name:'Arnold'}),\n" +
            "(charlie:Person {name:'Charlie'}),\n" +
            "(gordon:Person {name:'Gordon'}),\n" +
            "(lucy:Person {name:'Lucy'}),\n" +
            "(emily:Person {name:'Emily'}),\n" +
            "(ian:Person {name:'Ian'}),\n" +
            "(kate:Person {name:'Kate'}),\n" +
            "(acme:Company {name:'Acme, Inc'}),\n" +
            "(startup:Company {name:'Startup, Ltd'}),\n" +
            "(neo4j:Skill {name:'Neo4j'}),\n" +  //graphs
            "(rest:Skill {name:'REST'}),\n" +
            "(dotNet:Skill {name:'DotNet'}),\n" +  //art
            "(ruby:Skill {name:'Ruby'}),\n" +  //design
            "(sql:Skill {name:'SQL'}),\n" +    // medicine
            "(architecture:Skill {name:'Architecture'}),\n" + //drama
            "(java:Skill {name:'Java'}),\n" +
            "(python:Skill {name:'Python'}),\n" +    //music
            "(javascript:Skill {name:'Javascript'}),\n" +    //cars
            "(clojure:Skill {name:'Clojure'}),\n" +    //travel
            "(phoenix:Project {name:'Phoenix'}),\n" +
            "(quantumLeap:Project {name:'Quantum Leap'}),\n" +
            "(nextGenPlatform:Project {name:'Next Gen Platform'}),\n" +
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
}
