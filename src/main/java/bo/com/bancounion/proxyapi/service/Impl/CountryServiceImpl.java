package bo.com.bancounion.proxyapi.service.Impl;

import bo.com.bancounion.proxyapi.client.dto.CountryDto;
import bo.com.bancounion.proxyapi.client.service.ICountryRestClient;
import bo.com.bancounion.proxyapi.response.CountryResponse;
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
    public HandlerResponse getCountryByIp(String ip) {
        String message = "Success";
        int httpCode = Response.Status.OK.getStatusCode();

        HandlerResponse.MetadataResponse metadataResponse = new HandlerResponse.MetadataResponse();
        CountryResponse countryResponse = new CountryResponse();

        CountryDto countryDto = null;

        try{
            countryDto = client.getCountryByIp(ip, ApiKey);
            countryResponse.setCountry(countryDto);
        }catch (Exception ex){
            System.out.println("Error al obtener pais por IP. Message: " + ex.getMessage());
            message = "Fail";
            httpCode = Response.Status.BAD_REQUEST.getStatusCode();
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);

        return new HandlerResponse(metadataResponse, countryResponse);
    }
}
