package part14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage window) {
        List<String> lines = new ArrayList<>();
        Map<String, Integer> dataMap = new HashMap<>();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();

        readFile(lines);
        for (String line : lines) {
            dataMap.put(line.split(", ")[0], Integer.valueOf(line.split(", ")[1]));

            // dataSeries.getData().add(new XYChart.Data<>(line.split(", ")[0],
            // Integer.valueOf(line.split(", ")[1])));

        }
        System.out.println(dataMap);
        List<Map.Entry<String, Integer>> sortedData = new ArrayList<>(dataMap.entrySet());
        Collections.sort(sortedData, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });

        for (Map.Entry<String, Integer> pair : sortedData) {
            dataSeries.getData().add(new XYChart.Data<>(pair.getKey(), pair.getValue()));

        }

        barChart.getData().add(dataSeries);

        Scene scene = new Scene(barChart);

        window.setScene(scene);
        window.show();
    }

    public void readFile(List<String> lines) {
        try {
            BufferedReader buffread = new BufferedReader(new FileReader("data.txt"));
            buffread.lines().forEach(line -> lines.add(line));
            buffread.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}