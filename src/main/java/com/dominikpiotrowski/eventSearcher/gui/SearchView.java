package com.dominikpiotrowski.eventSearcher.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@Route("search")
public class SearchView extends HorizontalLayout {

    private H3 header = new H3("Please specify seach parameters:");
    private TextField name = new TextField("Name"); //te pola mają odp polom serwisu
    private TextField city = new TextField("City");
    DatePicker startDate = new DatePicker("Start date");
    DatePicker endDate = new DatePicker("End date");
    private Button submit = new Button("Submit"); //submit ma wysłać dane do serwisu
    private Button clear = new Button("Clear");

    public SearchView() {
        setSizeUndefined();
        startDate.setLabel("Select start date");
        endDate.setLabel("Select end date");
        submit.setThemeName("primary");

        HorizontalLayout formLayout = new HorizontalLayout(startDate, endDate, city, name);
        VerticalLayout buttonLayout = new VerticalLayout(header, submit, clear);

        add(formLayout, buttonLayout);
    }

    public Map<String, Object> inputParams() {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("startDate", startDate.getValue());
        parameters.put("endDate", endDate.getValue());
        parameters.put("city", city.getValue());
        parameters.put("name", name.getValue());

        return inputParams();
    }
}