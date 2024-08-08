package bo.com.bancounion.proxyapi.service.Impl;

import bo.com.bancounion.proxyapi.client.dto.CountryDto;
import bo.com.bancounion.proxyapi.client.dto.Show;
import bo.com.bancounion.proxyapi.client.service.ICountryRestClient;
import bo.com.bancounion.proxyapi.response.HandlerResponse;
import bo.com.bancounion.proxyapi.service.CountryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class CountryServiceImpl implements CountryService {

    @RestClient
    ICountryRestClient client;

    @Inject
    @ConfigProperty(name = "ip.country.api.key")
    String ApiKey;

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

    @Override
    public HandlerResponse getCountryByIp(String ip) {
        String message = "Success";
        Integer httpCode = Response.Status.OK.getStatusCode();

        HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
        HandlerResponse.DataResponse dataResponse = new HandlerResponse.DataResponse();

        CountryDto countryDto = new CountryDto();

        try{
            //apiKey = System.getenv("IP_COUNTRY_API_KEY");
            countryDto = client.getCountryByIp(ip, ApiKey);

        }catch (Exception ex){
            System.out.println("Error al obtener listado de shows por ID. Message: " + ex.getMessage());
            message = "Fail";
            httpCode = Response.Status.BAD_REQUEST.getStatusCode();
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);
        dataResponse.setData(countryDto);

        HandlerResponse handlerResponse = new HandlerResponse(metadataResponse, dataResponse);

        return handlerResponse;
    }
}
