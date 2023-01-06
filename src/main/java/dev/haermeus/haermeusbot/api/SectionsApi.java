package dev.haermeus.haermeusbot.api;

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

    @Path("/children/resources")
    List<PlainSectionDTO> getChildrenSections(@QueryParam("id") long sectionId);
}
