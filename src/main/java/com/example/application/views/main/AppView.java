package com.example.application.views.main;

import java.util.HashMap;
import java.util.Map;

import com.example.application.Calculator;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("App")
@Route(value = "")
public class AppView extends AppLayout {

    private final Map<Tab, VerticalLayout> tabToViewMap;
    private final Tabs viewTabs;
    private final Tab calcTab;
    private final Tab settingTab;

    public AppView() {
        tabToViewMap = new HashMap<>();

        calcTab = createTabAndLinkToView(new CalcView(), "Calculator");
        settingTab = createTabAndLinkToView(new SettingView(), "Settings");

        viewTabs = new Tabs(calcTab, settingTab);
        viewTabs.setWidthFull();

        viewTabs.addSelectedChangeListener(event -> {
            final Tab selectedTab = event.getSelectedTab();
            final VerticalLayout view = tabToViewMap.get(selectedTab);
            setContent(view);
        });

        addToNavbar(true, viewTabs);
        click(calcTab);
        getElement().executeJs(JavaScripts.USE_SYSTEM_THEME_SCRIPT);
    }

    private Tab createTabAndLinkToView(VerticalLayout view, String tabText) {
        final Tab tab = new Tab(tabText);
        this.tabToViewMap.put(tab, view);
        return tab;
    }

    private void click(Tab tab) {
        viewTabs.setSelectedTab(tab);
        final Component view = tabToViewMap.get(tab);
        setContent(view);
    }
}

class CalcView extends VerticalLayout {
    private final TextField textFieldA, textFieldB;
    private final Button multiplyButton;
    private final Calculator calculator;

    public CalcView() {
        calculator = new Calculator();
        NativeLabel label = new NativeLabel("Multiply");
        textFieldA = new TextField("Number a");
        textFieldB = new TextField("Number b");
        multiplyButton = new Button("Multiply");
        multiplyButton.addClickListener(e -> {
            int valueA = Integer.parseInt(textFieldA.getValue());
            int valueB = Integer.parseInt(textFieldB.getValue());
            int result = calculator.multiply(valueA, valueB);
            Notification.show(result + "");
        });

        setMargin(true);
        add(label,textFieldA, textFieldB, multiplyButton);
    }
}

class SettingView extends VerticalLayout {
    private static final String SETTINGS = "settings";

    public SettingView() {
        add(SETTINGS);
    }
}