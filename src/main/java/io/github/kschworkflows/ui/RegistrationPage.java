package io.github.kschworkflows.ui;

import io.github.kschworkflows.services.PatientService;
import io.github.kschworkflows.services.PatientServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class RegistrationPage extends Page {

    private PatientService patientService = PatientServiceFactory.getPatientService();

    private ObservableList<PatientProperty> patients = FXCollections.observableArrayList();

    public RegistrationPage(ClinicalServiceApplication application) {
        super(application);

        System.out.println("Number of patients with name 'Doe': " + patientService.findPatients("Doe").size());
    }

    @Override
    public Pane getContents() {

        FlowPane topPane = new FlowPane();

        Button goHomeButton = new Button("Home");
        goHomeButton.setOnAction(event -> application.setScene(new HomePage(application)));

        topPane.getChildren().add(goHomeButton);

        TableColumn<PatientProperty, String> patientIdColumn = new TableColumn<>("ID");
        patientIdColumn.setMinWidth(15);
        patientIdColumn.setCellValueFactory(cell -> cell.getValue().idProperty());

        TableColumn<PatientProperty, String> patientNameColumn = new TableColumn<>("Name");
        patientIdColumn.setMinWidth(30);
        patientIdColumn.setCellValueFactory(cell -> cell.getValue().displayNameProperty());

        TableView<PatientProperty> tableView = new TableView<>(patients);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefHeight(175);
        tableView.getColumns().add(patientIdColumn);
        tableView.getColumns().add(patientNameColumn);

        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(tableView);

        return root;
    }
 }
