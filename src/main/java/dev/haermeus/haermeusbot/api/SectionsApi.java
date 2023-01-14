package dev.haermeus.haermeusbot.api;

import dev.haermeus.haermeusbot.dto.resource.PlainResourceDTO;
import dev.haermeus.haermeusbot.dto.section.PlainSectionDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/sections")
public interface SectionsApi {

    @GET
    @Path("/plain")
    @Produces("application/json")
    PlainSectionDTO getPlainSection(@QueryParam("id") long sectionId);

    @GET
    @Path("/children/sections")
    @Produces("application/json")
    List<PlainSectionDTO> getChildrenSections(@QueryParam("id") long sectionId);

    @GET
    @Path("/roots")
    @Produces("application/json")
    List<PlainSectionDTO> getRootSections();

    @GET
    @Path("/children/resources")
    @Produces("application/json")
    List<PlainResourceDTO> getChildrenResources(@QueryParam("id") long sectionId);
}
