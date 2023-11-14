package com.example.application.views.main;

import com.example.application.Calculator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;






public class AddView extends VerticalLayout {
    private final TextField textFieldA, textFieldB;
    private final Button addButton;
    private final Calculator calculator;


    public AddView() {
        calculator = new Calculator();
        NativeLabel label = new NativeLabel("Add");
        textFieldA = new TextField("Number a");
        textFieldB = new TextField("Number b");
        addButton = new Button("Add");
        addButton.addClickListener(e -> {
            int valueA = Integer.parseInt(textFieldA.getValue());
            int valueB = Integer.parseInt(textFieldB.getValue());
            int result = calculator.add(valueA, valueB);
            Notification.show(result + "");
        });

        setMargin(true);
        add(label,textFieldA, textFieldB, addButton);
    }
}
