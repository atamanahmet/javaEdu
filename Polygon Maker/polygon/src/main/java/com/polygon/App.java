package com.polygon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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

        // super(new Polygon(20, -20, 20, 20, -20, 20, -20, -20), x, y);
        Double point1 = 20.0;
        Double point2 = -20.0;
        Double point3 = 20.0;
        Double point4 = 20.0;
        Double point5 = -20.0;
        Double point6 = 20.0;
        Double point7 = -20.0;
        Double point8 = -20.0;
        Polygon polygon = new Polygon(point1, point2, point3, point4, point5, point6, point7, point8);
        System.out.println(polygon.getPoints());

        polygon.setTranslateX(300);
        polygon.setTranslateY(200);

        TextField Position = new TextField();
        Button button = new Button("save");
        BorderPane borderPane = new BorderPane();

        TextField text1 = new TextField();
        TextField text2 = new TextField();

        text1.setText(String.valueOf(point1));
        text2.setText(String.valueOf(point2));

        HBox hbox = new HBox(text1, text2, button);
        Pane pane = new Pane();

        button.setOnAction(e -> {
            pane.getChildren().clear();

            double newpoint1 = Double.valueOf(text1.getText());
            double newpoint2 = Double.valueOf(text2.getText());

            Polygon poly = new Polygon(newpoint1, newpoint2, point3, point4, point5, point6, point7, point8);
            poly.setFill(Color.BLACK);
            poly.setTranslateX(300);
            poly.setTranslateY(200);
            pane.getChildren().add(poly);

        });

        borderPane.setCenter(pane);

        borderPane.setPrefSize(600, 400);
        borderPane.setTop(hbox);

        pane.setPrefSize(600, 400);

        pane.getChildren().add(polygon);

        Scene scene = new Scene(borderPane);

        // BackgroundImage myBI = new BackgroundImage(new
        // Image("https://picsum.photos/600/400", 600, 400, false, true),
        // BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
        // BackgroundPosition.DEFAULT,
        // BackgroundSize.DEFAULT);
        // borderPane.setBackground(new Background(myBI));
        // pane.setBackground(new Background(myBI));

        scene.setOnMouseClicked(e -> {
            double x = e.getSceneX() - 300;
            double y = e.getSceneY() - 200 + 25;
            System.out.println("x: " + x + "-y: " + y);
        });

        stage.setScene(scene);
        stage.show();
    }
}