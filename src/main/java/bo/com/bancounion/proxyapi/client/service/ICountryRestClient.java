package bo.com.bancounion.proxyapi.client.service;

import bo.com.bancounion.proxyapi.client.dto.Show;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient//(baseUri = "https://api.tvmaze.com")
public interface ICountryRestClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shows/{showId}")
    Show getShowById(@PathParam("showId") Integer showId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/shows")
    List<Show> getShows();
}
