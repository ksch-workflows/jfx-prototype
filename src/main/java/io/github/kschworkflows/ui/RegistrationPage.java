package io.github.kschworkflows.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class RegistrationPage extends Page {

    public RegistrationPage(ClinicalServiceApp application) {
        super(application);
    }

    @Override
    public Pane getPageContents() {

        FlowPane pane = new FlowPane();

        Button goHomeButton = new Button("Home");
        goHomeButton.setOnAction(event -> application.setScene(new HomePage(application)));

        pane.getChildren().add(goHomeButton);

        return pane;
    }
}
