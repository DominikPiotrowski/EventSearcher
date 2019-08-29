package com.dominikpiotrowski.eventSearcher.services;

import com.dominikpiotrowski.eventSearcher.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Value("${event.search.service.url}")
    private String url;

    private RestTemplate restTemplate;

    public EventService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Event> findEvents(
            Map<SearchParameters, Object> searchParams) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", searchParams.get(SearchParameters.START_DATE));
        parameters.put("endDate", searchParams.get(SearchParameters.END_DATE));
        parameters.put("city", searchParams.get(SearchParameters.CITY));
        parameters.put("name", searchParams.get(SearchParameters.NAME));

        ResponseEntity<List> response =
                restTemplate.getForEntity(createUrl(parameters), List.class);

        List<Event> results = (List<Event>) response.getBody()
                .stream()
                .map(x -> mapToEvent(x))
                .collect(Collectors.toList());
        return results;
    }

    private Event mapToEvent(Object obj) {
        LinkedHashMap x = (LinkedHashMap) obj;

        return new Event(
                x.get("name").toString(),
                x.get("city").toString(),
                x.get("description").toString());
    }

    private String createUrl(Map<String, Object> parameters) {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?");
        int i = 1;
        for (String key : parameters.keySet()) {

            if (parameters.get(key) != null) {
                stringBuilder.append(key).append("=").append(parameters.get(key));
                if (i < parameters.keySet().size()) {
                    stringBuilder.append("&");
                }
                i++;
            }
        }
        return stringBuilder.toString();
    }
}