package com.dominikpiotrowski.eventSearcher.gui;

import com.dominikpiotrowski.eventSearcher.Service.EventService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("/search")
public class SearchView extends FormLayout{

    private TextField name = new TextField("Name");
    private TextField city = new TextField("City");
    DatePicker startDate = new DatePicker();
    DatePicker endDate = new DatePicker();
    private Button submit = new Button("Submit");
    private Button clear = new Button("Clear");

    public SearchView(EventService eventService) {
        setSizeUndefined();
        VerticalLayout fields = new VerticalLayout(name, city, startDate, endDate);
        HorizontalLayout buttons = new HorizontalLayout(submit, clear);
        startDate.setLabel("Select start date");
        endDate.setLabel("Select end date");
        add(fields, buttons);
    }
}