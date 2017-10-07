package com.experimental.purchase;

import com.experimental.NavigationController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PurchaseController extends NavigationController implements Initializable, PurchaseInterface {
    
    @FXML
    private TableView<Purchase> purchaseTable;

    @FXML
    private TableColumn<Purchase, Long> idColumn;

    @FXML
    private TableColumn<Purchase, String> productColumn, supplierColumn, dateColumn;

    @FXML
    private TableColumn<Purchase, Double> quantityColumn, priceColumn, totalColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button menu;

    @FXML
    private VBox drawer;

    private PurchaseModel model;

    private double xOffset = 0;

    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        model = new PurchaseModel();

        loadData();
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColumn.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, String> p) -> 
                new SimpleStringProperty(p.getValue().getProduct().getProductName()));
        supplierColumn.setCellValueFactory((TableColumn.CellDataFeatures<Purchase, String> p)
                -> new SimpleStringProperty(p.getValue().getSupplier().getName()));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchaseTable.setItems(PURCHASELIST);
        
        filterData();
        
    }

    private void filterData() {
        FilteredList<Purchase> searchedData = new FilteredList<>(PURCHASELIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(purchase -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (purchase.getProduct().getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (purchase.getProduct().getCategory().getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    
                    return false;
                });
            });

            SortedList<Purchase> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(purchaseTable.comparatorProperty());
            purchaseTable.setItems(sortedData);
        });
    }
    
    private void loadData() {
    
        if(!PURCHASELIST.isEmpty()){
            PURCHASELIST.clear();
        }
        
        PURCHASELIST.addAll(model.getPurchases());
    }
    
    @FXML
    public void addAction(ActionEvent event) throws Exception {
    
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/purchase/Add.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        root.setOnMousePressed((MouseEvent e) -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent e) -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Purchase");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
