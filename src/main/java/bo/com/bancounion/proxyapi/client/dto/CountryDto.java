package bo.com.bancounion.proxyapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    String ip;
    String countryCode;
    String countryName;
    String city;
}
