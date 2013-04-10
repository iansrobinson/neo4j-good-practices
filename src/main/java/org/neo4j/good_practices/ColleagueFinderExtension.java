package org.neo4j.good_practices;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.neo4j.graphdb.GraphDatabaseService;

@Path("/similar-skills")
public class ColleagueFinderExtension
{
    private final ColleagueFinder colleagueFinder;

    public ColleagueFinderExtension( @Context GraphDatabaseService db )
    {
        this.colleagueFinder = new ColleagueFinder( db );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getDistance( @PathParam("name") String name ) throws IOException
    {
        Iterator<Map<String, Object>> results = colleagueFinder.findFor( name );
        String json = new ObjectMapper().writeValueAsString( results );

        return Response.ok().entity( json ).build();
    }
}
