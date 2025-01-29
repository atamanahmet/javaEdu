package part14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);

    }

    public void start(Stage window) {
        Image imageFile = new Image("file:LisaGirl.jpg");

        int width = (int) imageFile.getWidth();
        int height = (int) imageFile.getHeight();

        PixelReader pixelReader = imageFile.getPixelReader();

        WritableImage finalimage = new WritableImage(width, height);
        // WritableImage smallImage = new WritableImage(width / 2, height / 2);

        PixelWriter pixelWriter = finalimage.getPixelWriter();

        drawOriginImage(height, width, pixelReader, pixelWriter);
        drawSmallImage(height, width, pixelReader, pixelWriter);
        // copySmallImage(height, width, pixelReader, pixelWriter, height / 2, width /
        // 2);

        ImageView iView = new ImageView();

        GridPane grid = new GridPane();

        grid.add(new ImageView(finalimage), 0, 0);
        grid.add(new ImageView(finalimage), 0, width / 2);
        // grid.add(new ImageView(finalimage), 1, 1);
        // grid.add(new ImageView(finalimage), 1, 0);

        Pane pane = new Pane(new ImageView(finalimage));

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }

    public void drawOriginImage(int height, int width, PixelReader pixelReader, PixelWriter pixelWriter) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixelColor = pixelReader.getColor(j, i);
                double red = pixelColor.getRed();
                double green = pixelColor.getGreen();
                double blue = pixelColor.getBlue();
                double opacity = pixelColor.getOpacity();

                Color newColor = new Color(red, green, blue, opacity);

                pixelWriter.setColor(j, i, newColor);

            }
        }

    }

    public void drawSmallImage(int height, int width, PixelReader pixelReader, PixelWriter pixelWriter) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    Color smallColor = pixelReader.getColor(j * 2, i * 2);
                    double smallred = smallColor.getRed();
                    double smallgreen = smallColor.getGreen();
                    double smallblue = smallColor.getBlue();
                    double smallopacity = smallColor.getOpacity();

                    Color smallNewColor = new Color(smallred, smallgreen, smallblue, smallopacity);

                    pixelWriter.setColor(j, i, smallNewColor);
                } catch (Exception e) {
                    continue;
                }

            }
        }
    }

}