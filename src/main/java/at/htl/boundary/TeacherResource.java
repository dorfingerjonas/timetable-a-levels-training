package at.htl.boundary;

import at.htl.control.TeacherRepository;
import at.htl.entity.Teacher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;

@Path("/teacher")
@Produces(MediaType.APPLICATION_JSON)
public class TeacherResource {

    @Inject
    TeacherRepository repository;

    @GET
    public Response getAllTeachers() {
        return Response.ok(
            repository.findAll()
                .stream()
                .sorted(Comparator.comparing(Teacher::getLastName).reversed())
                .toArray()
        ).build();
    }

}
