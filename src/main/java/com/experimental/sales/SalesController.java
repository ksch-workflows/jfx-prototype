package com.experimental.sales;

import com.experimental.NavigationController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SalesController extends NavigationController implements Initializable, SaleInterface {

    @FXML
    private TableView<Sale> salesTable;
    @FXML
    private TableColumn<Sale, Long> idColumn;
    @FXML
    private TableColumn<Sale, String> productColumn, dateColumn;
    @FXML
    private TableColumn<Sale, Double> quantityColumn, priceColumn, totalColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button deleteButton;
    private SalesModel model;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        model = new SalesModel();

        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        productColumn.setCellValueFactory((TableColumn.CellDataFeatures<Sale, String> p) -> 
                new SimpleStringProperty(p.getValue().getProduct().getProductName()));
        
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        salesTable.setItems(SALELIST);
        
        filterData();
        
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(salesTable.getSelectionModel().getSelectedItems()));
    }

    private void filterData() {
        FilteredList<Sale> searchedData = new FilteredList<>(SALELIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(sale -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (sale.getProduct().getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (sale.getProduct().getCategory().getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    
                    return false;
                });
            });

            SortedList<Sale> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(salesTable.comparatorProperty());
            salesTable.setItems(sortedData);
        });
    }

    private void loadData(){
    
        if (!SALELIST.isEmpty()) {
            SALELIST.clear();
        }
        SALELIST.addAll(model.getSales());
    }


    @FXML
    public void deleteAction(ActionEvent event) throws Exception {
    }
}
