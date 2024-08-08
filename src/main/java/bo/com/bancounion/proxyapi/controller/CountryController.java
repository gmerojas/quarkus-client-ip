package bo.com.bancounion.proxyapi.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1/country")
public class CountryController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ip/{ip}")
    public String getCountry(@PathParam("ip") String ip) {
        return "IP: " + ip;
    }
}
