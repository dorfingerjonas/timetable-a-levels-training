package at.htl.boundary;

import at.htl.control.UnitRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/unit")
@Produces(MediaType.APPLICATION_JSON)
public class UnitResource {

    @Inject
    UnitRepository repository;

    @Path("class/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUnitsPerClass(@PathParam("id") String id) {
        return Response.ok(repository.find("schoolclass.id", id).list()).build();
    }
}
