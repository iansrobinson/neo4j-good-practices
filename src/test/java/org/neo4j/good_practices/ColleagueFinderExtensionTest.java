package org.neo4j.good_practices;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.test.server.HTTP;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class ColleagueFinderExtensionTest
{
    private ServerControls server;

    @Before
    public void startServer() throws IOException
    {
        server = TestServerBuilders.newInProcessBuilder()
                .withExtension( "/colleagues", ColleagueFinderExtension.class )
                .withFixture( ExampleData.smallGraph )
                .newServer();
    }

    @After
    public void stopServer()
    {
        server.close();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnColleaguesWithSimilarSkills() throws Exception
    {
        HTTP.Response response = HTTP.GET( server.httpURI().resolve( "/colleagues/similar-skills/Ian" ).toString() );

        assertEquals( 200, response.status() );
        assertEquals( MediaType.APPLICATION_JSON, response.header( "Content-Type" ));

        List<Map<String, Object>> results = new ObjectMapper().readValue( response.rawContent( ), List.class );

        assertEquals( "Lucy", results.get( 0 ).get( "name" ) );
        assertThat( (Iterable<String>) results.get( 0 ).get( "skills" ), hasItems( "Java", "Neo4j" ) );
    }
}
