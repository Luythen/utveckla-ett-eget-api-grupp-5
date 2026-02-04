package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;


@PermitAll // Alla har tillgång till denna klassen
@Path("/api/public") 
public class PublicResource {



    @GET
    @Produces(MediaType.TEXT_PLAIN)
        public String publicResource(){
        return "public";
    }

}
