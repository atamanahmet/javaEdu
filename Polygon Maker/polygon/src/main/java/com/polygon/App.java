package com.polygon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage stage) {

        // poly.getPoints().addAll(10.0, 10.0, 10.0, 10.0, 10.0, 10.0);

        // poly.setTranslateX(300);
        // poly.setTranslateY(200);
        // super(new Polygon(20, -20, 20, 20, -20, 20, -20, -20), x, y);
        Polygon polygon = new Polygon(5, 5, 15, 5, 15, 7, 5, 7);
        polygon.setTranslateX(300);
        polygon.setTranslateY(200);
        // polygon.setScaleX(0.5);
        // polygon.setScaleY(0.5);
        // polygon.setScaleZ(0.5);

        TextField Position = new TextField();
        // TextField yPosition = new TextField();
        Button button = new Button("save");
        BorderPane borderPane = new BorderPane();

        button.setOnAction(e -> {

            Double point1 = Double.valueOf(Position.getText().split(" ")[0]);
            Double point2 = Double.valueOf(Position.getText().split(" ")[1]);
            Double point3 = Double.valueOf(Position.getText().split(" ")[2]);
            Double point4 = Double.valueOf(Position.getText().split(" ")[3]);
            Double point5 = Double.valueOf(Position.getText().split(" ")[4]);
            Double point6 = Double.valueOf(Position.getText().split(" ")[5]);
            Polygon poly = new Polygon(point1, point2, point3, point4);
            poly.setFill(Color.BLACK);
            poly.setTranslateX(300);
            poly.setTranslateY(200);
            borderPane.setCenter(poly);

        });

        HBox hbox = new HBox(Position, button);

        borderPane.setCenter(polygon);

        borderPane.setPrefSize(600, 400);
        borderPane.setTop(hbox);

        Pane pane = new Pane();
        pane.setPrefSize(600, 400);

        pane.getChildren().add(polygon);

        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.show();
    }
}