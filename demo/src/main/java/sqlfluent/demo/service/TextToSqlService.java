package sqlfluent.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TextToSqlService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getSqlFromText(String query) throws JsonProcessingException {
        String url = "http://127.0.0.1:5000/generate-sql";

        // Prepare payload map
        Map<String, String> payload = new HashMap<>();
        payload.put("natural_language",query);

        // Convert to JSON string (for debugging)
        String jsonPayload = objectMapper.writeValueAsString(payload);
        System.out.println("Sending JSON payload: " + jsonPayload);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request entity with JSON string body
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        // Send POST request
        ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Object sql = response.getBody().get("sql");
            return sql != null ? sql.toString() : "No SQL returned";
        } else {
            return "Request failed with status: " + response.getStatusCode();
        }
    }
}
