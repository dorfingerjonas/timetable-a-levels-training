package at.htl.boundary;

import at.htl.control.SchoolclassRepository;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/class")
@Produces(MediaType.APPLICATION_JSON)
public class SchoolclassResource {

    @Inject
    SchoolclassRepository repository;

    @GET
    public Response getAllClasses() {
        return Response.ok(
            repository.findAll(Sort.ascending("id"))
                .stream()
                .toArray()
        ).build();
    }
}
