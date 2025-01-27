package part14;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class App extends Application {
    int rollCount = 100;

    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage window) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        BorderPane borderPane = new BorderPane();

        HBox hbox = new HBox();

        Button button100 = new Button("100 Rolls");
        Button button500 = new Button("500 Rolls");
        Button button1000 = new Button("1000 Rolls");

        Random randomNum = new Random();

        hbox.getChildren().addAll(button100, button500, button1000);

        yAxis.setLowerBound(1);
        yAxis.setUpperBound(6);
        yAxis.setTickUnit(1);
        yAxis.setMinorTickVisible(false);
        yAxis.setAutoRanging(false);

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            public Number fromString(String string) {
                int num = Integer.valueOf(string);
                return num;

            }

            public String toString(Number Number) {
                return String.valueOf(Number);
            }
        });
        button100.setOnAction(e -> {
            int sum = 0;
            XYChart.Series<Number, Number> dataPoints = new XYChart.Series<>();

            rollCount = 100;
            lineChart.getData().clear();
            for (int i = 1; i <= rollCount; i++) {
                sum += randomNum.nextInt(6) + 1;
                dataPoints.getData().add(new XYChart.Data<>(i, 1.0 * sum / i));

            }
            lineChart.getData().add(dataPoints);

        });
        button500.setOnAction(e -> {
            int sum = 0;
            XYChart.Series<Number, Number> dataPoints = new XYChart.Series<>();

            rollCount = 500;
            lineChart.getData().clear();

            for (int i = 1; i <= rollCount; i++) {
                sum += randomNum.nextInt(6) + 1;
                dataPoints.getData().add(new XYChart.Data<>(i, 1.0 * sum / i));

            }
            lineChart.getData().add(dataPoints);

        });
        button1000.setOnAction(e -> {
            int sum = 0;
            XYChart.Series<Number, Number> dataPoints = new XYChart.Series<>();

            rollCount = 1000;
            lineChart.getData().clear();

            for (int i = 1; i <= rollCount; i++) {
                sum += randomNum.nextInt(6) + 1;
                dataPoints.getData().add(new XYChart.Data<>(i, 1.0 * sum / i));

            }
            lineChart.getData().add(dataPoints);

        });

        // for (int i = 1; i <= rollCount; i++) {
        // int sum = 0;

        // sum += randomNum.nextInt(6) + 1;
        // dataPoints.getData().add(new XYChart.Data<>(i, 1.0 * sum / i));

        // }
        // lineChart.getData().add(dataPoints);

        borderPane.setTop(hbox);
        borderPane.setCenter(lineChart);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();
    }
}