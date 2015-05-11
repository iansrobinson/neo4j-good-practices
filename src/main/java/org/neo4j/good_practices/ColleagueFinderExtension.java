package org.neo4j.good_practices;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import org.codehaus.jackson.map.ObjectMapper;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.helpers.collection.IteratorUtil;

@Path("/similar-skills")
public class ColleagueFinderExtension
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ColleagueFinder colleagueFinder;
    private final Printer printer;

    public ColleagueFinderExtension( @Context GraphDatabaseService db, @Context Printer printer )
    {
        this.colleagueFinder = new ColleagueFinder( db );
        this.printer = printer;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getColleagues( @PathParam("name") String name ) throws IOException
    {
        printer.printLine( "GetColleagues() invoked" );

        String json = MAPPER.writeValueAsString(
                IteratorUtil.asCollection( colleagueFinder.findColleaguesFor( name ) ) );

        return Response.ok().entity( json ).build();
    }
}
