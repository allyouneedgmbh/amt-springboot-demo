package de.ayn.services.amt.rest;

import static java.util.Collections.emptyList;
import java.util.Map;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import de.ayn.services.amt.service.VersionService;

@Controller
@Path("public")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublicEndpoint {

    @Autowired
    private VersionService service;
    
    @GET
    @Path("version")
    public Result<Map<String, Object>>  version() {
        return new Result<>(Result.State.OK, service.simpleVersionInfo(), emptyList());
    }
    
    @GET
    @Path("greeting/{name}")
    public Result<String> greeting(@PathParam("name") String name) {
        String message = String.format("Hallo %s nice to see you again!", Optional.ofNullable(name).orElse("anonymous"));
        return new Result<>(Result.State.OK, message, emptyList());
    }


}
