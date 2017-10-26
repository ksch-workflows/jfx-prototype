package io.github.kschworkflows.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Start extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label title = new Label("K.S.C.H. Workflows - Clinical Service");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        title.setPadding(new Insets(10, 50, 10, 50));

        FlowPane sectionNavigation = new FlowPane(Orientation.HORIZONTAL);
        sectionNavigation.setStyle("-fx-background-color: transparent;");
        sectionNavigation.setPadding(new Insets(50, 50, 0, 50));
        sectionNavigation.setHgap(10);
        sectionNavigation.setVgap(10);

        Button goToRegistrationButton = new Button("Registration");
        goToRegistrationButton.setMinHeight(100);
        goToRegistrationButton.setPadding(new Insets(10, 30, 10, 30));

        Button goToPharmacyButton = new Button("Pharmacy");
        goToPharmacyButton.setMinHeight(100);
        goToPharmacyButton.setPadding(new Insets(10, 30, 10, 30));

        Button goToReportsButton = new Button("Reports");
        goToReportsButton.setMinHeight(100);
        goToReportsButton.setPadding(new Insets(10, 30, 10, 30));

        Button goToSettingsButton = new Button("Administration");
        goToSettingsButton.setMinHeight(100);
        goToSettingsButton.setPadding(new Insets(10, 30, 10, 30));

        sectionNavigation.getChildren().add(goToRegistrationButton);
        sectionNavigation.getChildren().add(goToPharmacyButton);
        sectionNavigation.getChildren().add(goToReportsButton);
        sectionNavigation.getChildren().add(goToSettingsButton);

        BorderPane welcomePageLayout = new BorderPane();
        welcomePageLayout.setTop(title);
        welcomePageLayout.setCenter(sectionNavigation);
        welcomePageLayout.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(welcomePageLayout, 1000, 500, Color.LIGHTGRAY);
        stage.setTitle("K.S.C.H. Workflows - Clinical Service");
        stage.setScene(scene);
        stage.show();
    }
}
