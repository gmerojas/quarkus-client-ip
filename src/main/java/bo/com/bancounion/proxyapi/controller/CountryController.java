package bo.com.bancounion.proxyapi.controller;

import bo.com.bancounion.proxyapi.response.HandlerResponse;
import bo.com.bancounion.proxyapi.service.CountryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/api/v1/country")
public class CountryController {

    @Inject
    private CountryService service;

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ip/{ip}")
    public Response getCountry(@PathParam("ip") String ip) {
        HandlerResponse handlerResponse = service.getShowsById2(Integer.parseInt(ip));
        return Response.status(handlerResponse.getMetadata().getHttpCode()).entity(handlerResponse).build();
    }*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/ip/{ip}")
    public Response getCountryByIp(@PathParam("ip") String ip) {
        HandlerResponse handlerResponse = service.getCountryByIp(ip);
        return Response.status(handlerResponse.getMetadata().getHttpCode()).entity(handlerResponse).build();
    }
}
