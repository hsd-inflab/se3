package com.example.application.views.main;

import com.example.application.Calculator;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

class CalculatorView extends VerticalLayout {
    private final TextField textFieldA, textFieldB;
    private final Button calculateButton;
    private final ComboBox<String> operationSelect;
    private final Calculator calculator;

    public CalculatorView() {
        calculator = new Calculator();
        textFieldA = new TextField("Number a");
        textFieldB = new TextField("Number b");

        // Dropdown for selecting the operation
        operationSelect = new ComboBox<>();
        operationSelect.setItems("Multiply");
        operationSelect.setValue("Multiply");

        calculateButton = new Button("Calculate");
        calculateButton.addClickListener(e -> performCalculation());

        setMargin(true);
        add(new NativeLabel("Calculator"), operationSelect, textFieldA, textFieldB, calculateButton);
    }

    private void performCalculation() {
        int valueA = Integer.parseInt(textFieldA.getValue());
        int valueB = Integer.parseInt(textFieldB.getValue());
        int result = 0;
        String operation = operationSelect.getValue();

        switch (operation) {
            case "Multiply":
                result = calculator.multiply(valueA, valueB);
                break;

        }
        Notification.show(result + "");
    }
}
