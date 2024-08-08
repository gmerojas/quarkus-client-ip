package bo.com.bancounion.proxyapi.service.Impl;

import bo.com.bancounion.proxyapi.client.dto.CountryDto;
import bo.com.bancounion.proxyapi.client.dto.ErrorDto;
import bo.com.bancounion.proxyapi.client.service.ICountryRestClient;
import bo.com.bancounion.proxyapi.response.CountryResponse;
import bo.com.bancounion.proxyapi.response.HandlerResponse;
import bo.com.bancounion.proxyapi.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
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
        ErrorDto errorDto = null;
        String entityResponse = null;
        Response response;

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        JsonObject jsonObject = new JsonObject();
        try{
            //countryDto = client.getCountryByIp(ip, ApiKey);
            response = client.getCountryByIp2(ip, ApiKey);
            entityResponse = response.readEntity(String.class);
            jsonObject = new JsonObject(entityResponse);
            countryDto = objectMapper.readValue(jsonObject.encode(), new TypeReference<CountryDto>() {});
            countryResponse.setCountry(countryDto);
        }catch (WebApplicationException ex){
            response = ex.getResponse();
            entityResponse = response.readEntity(String.class);
            jsonObject = new JsonObject(entityResponse);
            try {
                if (response.getStatus() == Response.Status.BAD_REQUEST.getStatusCode()){
                    errorDto = objectMapper.readValue(jsonObject.encode(), new TypeReference<ErrorDto>() {});
                    message = errorDto.getMessage().getInfo();
                    httpCode = errorDto.getMessage().getCode();
                }else{
                    countryResponse.setCountry(errorDto);
                    message = "Fail";
                    httpCode = Response.Status.BAD_REQUEST.getStatusCode();
                }
            } catch (JsonProcessingException e) {
                countryResponse.setCountry(errorDto);
                message = "Fail";
                httpCode = Response.Status.BAD_REQUEST.getStatusCode();
            }
        }catch (Exception ex){
            message = "Fail";
            httpCode = Response.Status.BAD_REQUEST.getStatusCode();
            countryResponse.setCountry(errorDto);
        }

        metadataResponse.setMessage(message);
        metadataResponse.setHttpCode(httpCode);

        return new HandlerResponse(metadataResponse, countryResponse);
    }
}
