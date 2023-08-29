package com.example.application.views.main;

import com.example.application.Calculator;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
public class MainView extends HorizontalLayout {

    private final TextField a, b;
    private final Button multiply;
    private final Calculator calculator;

    public MainView() {
        calculator = new Calculator();
        a = new TextField("Number a");
        b = new TextField("Number b");
        multiply = new Button("Multiply");
        multiply.addClickListener(e -> {
            Notification.show(calculator.multiply(Integer.parseInt(a.getValue()), Integer.parseInt(b.getValue())) + "");
        });
        multiply.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, a, b, multiply);

        add(a, b, multiply);
    }

}
