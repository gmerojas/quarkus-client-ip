package bo.com.bancounion.proxyapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryDto {
    private String ip;
    private String continentCode;
    private String continentName;
    private String countryCode;
    private String countryName;
    private String countryNameNative;
    private String officialCountryName;
    private String regionCode;
    private String regionName;
    private int cityGeoNameId;
    private String city;
    private String postalCode;
    private double latitude;
    private double longitude;
    private String capital;
    private String phoneCode;
    private String countryFlagEmoj;
    private String countryFlagEmojUnicode;
    private boolean isEu;
    private ArrayList<String> borders;
    private ArrayList<String> topLevelDomains;
}
