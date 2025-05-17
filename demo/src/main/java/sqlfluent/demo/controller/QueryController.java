
package sqlfluent.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class QueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/run-query")
    public Map<String, Object> runQuery(@RequestBody Map<String, String> payload) {
        String query = payload.get("query");

        Map<String, Object> response = new HashMap<>();

        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
            response.put("results", results);
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }

        return response;
    }
}
