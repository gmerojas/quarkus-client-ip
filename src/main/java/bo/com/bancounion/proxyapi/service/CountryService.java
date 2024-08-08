package bo.com.bancounion.proxyapi.service;

import bo.com.bancounion.proxyapi.response.HandlerResponse;

public interface CountryService {

    HandlerResponse getCountryByIp(String ip);

}
