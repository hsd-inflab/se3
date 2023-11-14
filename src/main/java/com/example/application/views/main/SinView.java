package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

class SinView extends VerticalLayout {
    private final TextField textFieldA;
    private final Button sinButton;
    private final AddSin AddSin;

    public SinView() {
        AddSin = new AddSin();
        NativeLabel label = new NativeLabel("Sin");
        textFieldA = new TextField("Number a");
        sinButton = new Button("Sin");
        sinButton.addClickListener(e -> {
            double valueA = Double.parseDouble(textFieldA.getValue());
           
            double result = AddSin.addsin(valueA);
            Notification.show(result + "");
        });

        setMargin(true);
        add(label,textFieldA, sinButton);
    }
}
