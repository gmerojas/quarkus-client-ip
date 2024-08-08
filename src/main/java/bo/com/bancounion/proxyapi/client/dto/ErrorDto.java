package bo.com.bancounion.proxyapi.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorDto {
    public boolean success;
    public Message message;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Message{
        public int code;
        public String type;
        public String info;
    }
}
