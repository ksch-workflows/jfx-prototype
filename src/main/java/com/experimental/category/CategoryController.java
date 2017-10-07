package com.experimental.category;

import com.experimental.NavigationController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class CategoryController extends NavigationController implements Initializable, CategoryInterface {

    @FXML
    private TableView<Category> categoryTable;
    @FXML
    private TableColumn<Category, Long> idColumn;
    @FXML
    private TableColumn<Category, String> typeColumn, descriptionColumn;
    @FXML
    private TextField searchField;
    @FXML
    private Button editButton, deleteButton;
    private CategoryModel model;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        model = new CategoryModel();

        loadData();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        categoryTable.setItems(CATEGORYLIST);

        filterData();

        editButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
        deleteButton
                .disableProperty()
                .bind(Bindings.isEmpty(categoryTable.getSelectionModel().getSelectedItems()));
    }

    private void filterData() {
        FilteredList<Category> searchedData = new FilteredList<>(CATEGORYLIST, e -> true);
        searchField.setOnKeyReleased(e -> {
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                searchedData.setPredicate(category -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (category.getType().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (category.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Category> sortedData = new SortedList<>(searchedData);
            sortedData.comparatorProperty().bind(categoryTable.comparatorProperty());
            categoryTable.setItems(sortedData);
        });
    }

    private void loadData() {

        if (!CATEGORYLIST.isEmpty()) {
            CATEGORYLIST.clear();
        }
        CATEGORYLIST.addAll(model.getCategories());
    }

    @FXML
    public void addAction(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/category/Add.fxml"));
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
        stage.setTitle("New Category");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editAction(ActionEvent event) throws Exception {

        Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();
        int selectedCategoryId = categoryTable.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/fxml/category/Edit.fxml")));
        EditController controller = new EditController();
        loader.setController(controller);
        Parent root = loader.load();
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
        stage.setTitle("Edit Category");
        stage.getIcons().add(new Image("/images/logo.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        controller.setCategory(selectedCategory, selectedCategoryId);
        categoryTable.getSelectionModel().clearSelection();
    }

    @FXML
    public void deleteAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Category selectedCategory = categoryTable.getSelectionModel().getSelectedItem();

            model.deleteCategory(selectedCategory);
            CATEGORYLIST.remove(selectedCategory);
        }

        categoryTable.getSelectionModel().clearSelection();
    }
}
