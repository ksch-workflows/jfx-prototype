package io.github.kschworkflows;

import io.github.kschworkflows.openmrs.CustomizedOpenMRS;
import io.github.kschworkflows.common.ui.Page;
import io.github.kschworkflows.login.HomePage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClinicalServiceApplication extends Application {

    public static final Color BACKGROUND_COLOR = Color.LIGHTGRAY;
    public static final double INITIAL_STAGE_WIDTH = 1000;
    public static final double INITIAL_STAGE_HEIGTH = 500;

    private Stage primaryStage;
    private Scene currentScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        setLoginCredentials();

        primaryStage.setTitle("K.S.C.H. Workflows - Clinical Service");
        setScene(new HomePage(this));
        primaryStage.show();
    }

    public void setScene(Page page) {
        if (currentScene == null) {
            currentScene = new Scene(page.getContents(), INITIAL_STAGE_WIDTH, INITIAL_STAGE_HEIGTH, BACKGROUND_COLOR);
        } else {
            currentScene = new Scene(page.getContents(), currentScene.getWidth(), currentScene.getHeight(), currentScene.getFill());
        }
        if (primaryStage == null) {
            throw new IllegalStateException("Stage not initialized yet.");
        }
        primaryStage.setScene(currentScene);
    }

    private void setLoginCredentials() {
        CustomizedOpenMRS.init("https://ksch/openmrs", "superman", "Admin123");
    }
}
