package dev.haermeus.haermeusbot.api;

import dev.haermeus.haermeusbot.dto.resource.PlainResourceDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/resources")
public interface ResourcesApi {

    @GET
    @Path("/plain")
    @Produces("application/json")
    PlainResourceDTO getPlainResource(@QueryParam("id") long resourceId);
}
