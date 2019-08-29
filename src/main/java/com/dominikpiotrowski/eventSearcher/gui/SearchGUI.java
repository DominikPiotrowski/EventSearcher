package com.dominikpiotrowski.eventSearcher.gui;

import com.dominikpiotrowski.eventSearcher.model.Event;
import com.dominikpiotrowski.eventSearcher.services.EventService;
import com.dominikpiotrowski.eventSearcher.services.SearchParameters;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value="search")
public class SearchGUI extends HorizontalLayout {

    H3 header = new H3("Please specify seach parameters:");
    TextField name = new TextField("Name"); //te pola mają odp polom serwisu
    TextField city = new TextField("City");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End date");
    Button submit = new Button("Submit");

    @Autowired
    public SearchGUI(EventService eventService) {

        setSizeUndefined();
        startDate.setLabel("Select start date");
        endDate.setLabel("Select end date");
        submit.setThemeName("primary");

        VerticalLayout mainView = new VerticalLayout(header);

        HorizontalLayout searchParams = new HorizontalLayout(startDate, endDate, city, name);
        HorizontalLayout buttonLayout = new HorizontalLayout(submit);

        Grid results = new Grid(Event.class);

        mainView.add(searchParams, buttonLayout, results);
        add(mainView);

        submit.addClickListener(clickEvent -> {
            List<Event> events = eventService.findEvents(inputParams());
            results.setItems(events);
            results.getDataProvider().refreshAll();
        });
    }

    public Map<SearchParameters, Object> inputParams() {
        Map<SearchParameters, Object> parameters = new HashMap<>();

        LocalDate startDateValue = startDate.getValue() != null ? startDate.getValue() : LocalDate.now();
        parameters.put(SearchParameters.START_DATE, startDateValue);

        LocalDate endDateValue = endDate.getValue() != null ? endDate.getValue() : LocalDate.now().plusDays(30);
        parameters.put(SearchParameters.END_DATE, endDateValue);

        String cityValue = city.getValue() != null ? city.getValue() : "";
        parameters.put(SearchParameters.CITY, cityValue);

        String nameValue = name != null ? name.getValue() : "";
        parameters.put(SearchParameters.NAME, nameValue);

        return parameters;
    }
}