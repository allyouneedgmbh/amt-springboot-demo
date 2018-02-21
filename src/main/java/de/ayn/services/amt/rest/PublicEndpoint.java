package de.ayn.services.amt.rest;

import static java.util.Collections.emptyList;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @GET
    @Path("next-2-method")
    public Result<String> next2method() {
        return new Result<>(Result.State.OK, "next-2-method", emptyList());
    }

    @GET
    @Path("next-3-method")
    public Result<String> next3method() {
        return new Result<>(Result.State.OK, "next-3-method", emptyList());
    }
    
    @GET
    @Path("auth")
    public Result<String> auth(@QueryParam("data") String data) {
        SecretKeySpec key = new SecretKeySpec("DiesIstEinTest".getBytes(), "HmacSHA1");
        try (Formatter formatter = new Formatter()) {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
            for (byte current : mac.doFinal(data.getBytes())) {
                formatter.format("%02x", current);
            }
            return new Result<>(Result.State.OK, formatter.toString(), emptyList());
        }
        catch (RuntimeException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new Result<>(Result.State.ERROR,null, Arrays.asList(e.getMessage()));
        }
    }
    
    
    @GET
    @Path("staticString")
    public Result<String> getStaticString() {
        return new Result<>(Result.State.OK,"TEST 4",Collections.emptyList());
    }

    
}
