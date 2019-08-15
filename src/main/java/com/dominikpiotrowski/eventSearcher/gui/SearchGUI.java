package com.dominikpiotrowski.eventSearcher.gui;

import com.dominikpiotrowski.eventSearcher.Service.EventService;
import com.dominikpiotrowski.eventSearcher.Service.SearchParameters;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route("search")
public class SearchGUI extends HorizontalLayout {

    H3 header = new H3("Please specify seach parameters:");
    TextField name = new TextField("Name"); //te pola mają odp polom serwisu
    TextField city = new TextField("City");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End date");
    Button submit = new Button("Submit");
    Button clear = new Button("Clear");

    @Autowired
    public SearchGUI(EventService eventService) {

        setSizeUndefined();
        startDate.setLabel("Select start date");
        endDate.setLabel("Select end date");
        submit.setThemeName("primary");

        HorizontalLayout formLayout = new HorizontalLayout(startDate, endDate, city, name);
        VerticalLayout buttonLayout = new VerticalLayout(header, submit, clear);
        add(formLayout, buttonLayout);

        submit.addClickListener(clickEvent -> eventService.findEvents(inputParams()));
    }

    public Map<SearchParameters, Object> inputParams() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("startDate", startDate.getValue());
        parameters.put("endDate", endDate.getValue());
        parameters.put("city", city.getValue());
        parameters.put("name", name.getValue());

        return inputParams();
    }
}