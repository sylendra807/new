package sqlfluent.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import sqlfluent.demo.service.SqlExecutorService;
import sqlfluent.demo.service.TextToSqlService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nlq")
public class QueryController {

    @Autowired
    private TextToSqlService textToSqlService;

    @Autowired
    private SqlExecutorService sqlExecutorService;

    @PostMapping
    public List<?> runNaturalLanguageQuery(@RequestBody Map<String, String> payload) throws JsonProcessingException {
        String naturalLanguageQuery = payload.get("natural_language");
        
        if (naturalLanguageQuery == null || naturalLanguageQuery.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing 'natural_language' in request body");
        }

        System.out.println("Received natural language query: " + naturalLanguageQuery);

        String sqlQuery = textToSqlService.getSqlFromText(naturalLanguageQuery);
        System.out.println("Generated SQL query: " + sqlQuery);

        if (sqlQuery == null || sqlQuery.trim().isEmpty()) {
            throw new RuntimeException("SQL generation failed or returned empty.");
        }

        return sqlExecutorService.runQuery(sqlQuery);
    }
}
