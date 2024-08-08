package bo.com.bancounion.proxyapi.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@RegisterForReflection
public class CountryResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object country;
}
