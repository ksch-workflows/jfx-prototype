package io.github.kschworkflows;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        StackPane root = new StackPane();

        Circle circle = new Circle(30);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(5);
        circle.setOnMouseClicked(event -> System.out.println("Circle clicked."));
        Text text = new Text("Hello");

        root.getChildren().addAll(circle, text);

        Scene scene = new Scene(root, 600, 300);
        stage.setTitle("Hello, World");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
