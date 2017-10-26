package io.github.kschworkflows.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ClinicalServiceApp extends Application {

    private Stage primaryStage;

    private Scene currentScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("K.S.C.H. Workflows - Clinical Service");
        setScene(new HomePage(this));
        primaryStage.show();
    }

    public void setScene(Page page) {
        if (currentScene == null) {
            currentScene = new Scene(page.getPageContents(), 1000, 500, Color.LIGHTGRAY);
        } else {
            currentScene = new Scene(page.getPageContents(), currentScene.getWidth(), currentScene.getHeight(), currentScene.getFill());
        }
        if (primaryStage == null) {
            throw new IllegalStateException("Stage not initialized yet.");
        }
        primaryStage.setScene(currentScene);
    }
}
