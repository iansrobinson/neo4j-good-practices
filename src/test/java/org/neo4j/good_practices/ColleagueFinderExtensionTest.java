package org.neo4j.good_practices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.server.CommunityNeoServer;
import org.neo4j.server.helpers.ServerBuilder;

public class ColleagueFinderExtensionTest
{
    private static CommunityNeoServer server;
    private static GraphDatabaseService db;

    @BeforeClass
    public static void startServer() throws IOException
    {
        server = ServerBuilder.server()
                .withThirdPartyJaxRsPackage(
                        "org.neo4j.good_practices",
                        "/colleagues" )
                .build();
        server.start();

        db = server.getDatabase().getGraph();
        ExampleGraph.populate( db );
    }

    @AfterClass
    public static void stopServer()
    {
        server.stop();
    }

    @Test
    @SuppressWarnings( "unchecked" )
    public void shouldReturnColleaguesWithSimilarSkills() throws Exception
    {
        Client client = Client.create( new DefaultClientConfig() );

        WebResource resource = client
                .resource( "http://localhost:7474/colleagues/similar-skills/Sue" );
        ClientResponse response = resource
                .accept( MediaType.APPLICATION_JSON )
                .get( ClientResponse.class );

        List<Map<String, Object>> results = new ObjectMapper().readValue( response.getEntity( String.class ), List.class );

        assertEquals( 200, response.getStatus() );
        assertEquals( MediaType.APPLICATION_JSON, response.getHeaders().get( "Content-Type" ).get( 0 ) );
        assertEquals( "Lucy", results.get( 0 ).get( "name" ) );
        assertThat( (Iterable<String>) results.get( 0 ).get( "skills" ), hasItems( "Java", "Neo4j" ) );
    }
}
