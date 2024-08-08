package bo.com.bancounion.proxyapi.service.Impl;

import bo.com.bancounion.proxyapi.client.dto.Show;
import bo.com.bancounion.proxyapi.client.service.ICountryRestClient;
import bo.com.bancounion.proxyapi.response.HandlerResponse;
import bo.com.bancounion.proxyapi.service.CountryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CountryServiceImpl implements CountryService {

    @RestClient
    ICountryRestClient client;
    @Override
    public HandlerResponse getShowsById2(Integer showId) {
        String message = "Success";
        Integer httpCode = Response.Status.OK.getStatusCode();

        HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
        HandlerResponse.DataResponse dataResponse = new HandlerResponse.DataResponse();

        Show show = new Show();

        try{

            show = client.getShowById(showId);

        }catch (Exception ex){
            System.out.println("Error al obtener listado de shows por ID. Message: " + ex.getMessage());
            message = "Fail";
            httpCode = Response.Status.BAD_REQUEST.getStatusCode();
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);
        dataResponse.setData(show);

        HandlerResponse handlerResponse = new HandlerResponse(metadataResponse, dataResponse);

        return handlerResponse;
    }
}
