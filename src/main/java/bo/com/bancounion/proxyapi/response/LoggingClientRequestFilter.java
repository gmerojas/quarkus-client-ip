package bo.com.bancounion.proxyapi.response;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

import java.io.IOException;

public class LoggingClientRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        // Obtén la URL de la solicitud
        String url = requestContext.getUri().toString();
        System.out.println("Solicitando URL: " + url);
    }
}