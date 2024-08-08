package bo.com.bancounion.proxyapi.client.service;

import bo.com.bancounion.proxyapi.client.dto.CountryDto;
import bo.com.bancounion.proxyapi.client.dto.Show;
import bo.com.bancounion.proxyapi.response.LoggingClientRequestFilter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient//(baseUri = "https://api.tvmaze.com")
@RegisterProvider(LoggingClientRequestFilter.class)
public interface ICountryRestClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/check")
    CountryDto getCountryByIp(@QueryParam("ip") String ip, @QueryParam("accessKey") String accessKey);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shows/{showId}")
    Show getShowById(@PathParam("showId") Integer showId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shows")
    List<Show> getShows();
}
