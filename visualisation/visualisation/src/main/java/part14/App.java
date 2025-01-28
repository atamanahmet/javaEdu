package part14;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {
    Color color;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window) {
        // Canvas canvas = new Canvas(150, 150);
        // // ColorPicker colorPicker = new ColorPicker();
        // GraphicsContext painter = canvas.getGraphicsContext2D();
        BorderPane borderPane = new BorderPane();
        Image imageFile = new Image("file:test.jpg");

        int height = (int) imageFile.getHeight();
        int width = (int) imageFile.getWidth();

        PixelReader reader = imageFile.getPixelReader();
        WritableImage finalImage = new WritableImage(width, height);

        PixelWriter writer = finalImage.getPixelWriter();

        Slider redSlider = new Slider();
        Slider greenSlider = new Slider();
        Slider blueSlider = new Slider();

        redSlider.setMax(1);
        redSlider.setMin(0);
        redSlider.setMajorTickUnit(1);
        redSlider.setMajorTickUnit(0.5);
        redSlider.setShowTickLabels(true);

        greenSlider.setMax(1);
        greenSlider.setMin(0);
        greenSlider.setMajorTickUnit(1);
        greenSlider.setMajorTickUnit(0.5);
        greenSlider.setShowTickLabels(true);

        blueSlider.setMax(1);
        blueSlider.setMin(0);
        blueSlider.setMajorTickUnit(1);
        blueSlider.setMajorTickUnit(0.5);
        blueSlider.setShowTickLabels(true);
        // redSlider.setSnapToTicks(true);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixelColor = reader.getColor(j, i);
                double red = pixelColor.getRed();
                redSlider.setValue(red);
                double blue = pixelColor.getBlue();
                blueSlider.setValue(blue);
                double green = pixelColor.getGreen();
                greenSlider.setValue(green);
                double alpha = pixelColor.getOpacity();
                Color newColor = new Color(red, green, blue, alpha);
                writer.setColor(j, i, newColor);
            }
        }
        // Slider red = new Slider();

        redSlider.setOnMouseReleased(e -> {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color pixelColor = reader.getColor(j, i);
                    double red = redSlider.getValue();
                    double blue = pixelColor.getBlue();
                    double green = pixelColor.getGreen();

                    double alpha = pixelColor.getOpacity();
                    Color newColor = new Color(red, green, blue, alpha);
                    writer.setColor(j, i, newColor);
                }
            }
        });
        blueSlider.setOnMouseReleased(e -> {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color pixelColor = reader.getColor(j, i);
                    double red = pixelColor.getRed();

                    double blue = blueSlider.getValue();
                    double green = pixelColor.getGreen();

                    double alpha = pixelColor.getOpacity();
                    Color newColor = new Color(red, green, blue, alpha);
                    writer.setColor(j, i, newColor);
                }
            }
        });
        greenSlider.setOnMouseReleased(e -> {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color pixelColor = reader.getColor(j, i);
                    double red = pixelColor.getRed();

                    double blue = pixelColor.getBlue();

                    double green = greenSlider.getValue();

                    double alpha = pixelColor.getOpacity();
                    Color newColor = new Color(red, green, blue, alpha);
                    writer.setColor(j, i, newColor);
                }
            }
        });

        ImageView view = new ImageView(finalImage);
        // view.setScaleX(0.5);
        // view.setScaleY(0.5);

        // // colorPicker.setOnMouseExited(e -> {

        // // });
        // painter.setStroke(Color.BLACK);

        // painter.setFill(Color.BLACK);
        // borderPane.setStyle("-fx-background-color: White");
        // System.out.println(borderPane.getMaxHeight());
        // double xPos = 50;
        // double yPos = 50;

        // painter.fillRect(55, 50, 10, 10);
        // painter.fillRect(95, 50, 10, 10);
        // painter.fillRect(45, 90, 10, 10);
        // painter.fillRect(55, 100, 10, 10);
        // painter.fillRect(65, 100, 10, 10);
        // painter.fillRect(75, 100, 10, 10);
        // painter.fillRect(85, 100, 10, 10);
        // painter.fillRect(95, 100, 10, 10);
        // painter.fillRect(105, 90, 10, 10);

        // // canvas.setOnMouseDragged((event) -> {
        // // double getX = event.getX();
        // // double getY = event.getY();

        // // painter.fillOval(getX, getY, 4, 4);
        // // });

        // // System.out.println(colorPicker.getValue());
        // // borderPane.setRight(colorPicker);

        Pane pane = new Pane();
        borderPane.setCenter(pane);
        pane.setPrefSize(640, 480);
        pane.getChildren().add(view);
        VBox sliderBox = new VBox();
        sliderBox.getChildren().addAll(redSlider, greenSlider, blueSlider);
        borderPane.setTop(sliderBox);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }
}
