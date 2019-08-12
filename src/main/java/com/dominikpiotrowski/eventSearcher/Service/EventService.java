package com.dominikpiotrowski.eventSearcher.Service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventService {

    private RestTemplate restTemplate;
    private String url;


    public List findEvents(
            Map<SearchParameters, Object> searchParams) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", searchParams.get(SearchParameters.START_DATE));
        parameters.put("endDate", searchParams.get(SearchParameters.END_DATE));
        parameters.put("city", searchParams.get(SearchParameters.CITY));
        parameters.put("name", searchParams.get(SearchParameters.NAME));

        ResponseEntity<List> response =
                restTemplate.getForEntity(createUrl(parameters), List.class);

        return response.getBody();
    }

    private String createUrl(Map<String, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?");
        int i = 1;
        for (String key : parameters.keySet()) {

            if (parameters.get(key) != null) {
                stringBuilder.append(key).append("=").append(parameters.get(key));
                if (i <= parameters.keySet().size() - 1) {
                    stringBuilder.append("&");
                }
                i++;
            }
        }
        return stringBuilder.toString();
    }
}