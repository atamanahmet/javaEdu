package part14;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(App.class);
    }

    public void start(Stage window) {

        List<String> lines = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        List<Integer> ranks = new ArrayList<>();
        Map<Integer, Integer> yearsWithRanks = new HashMap<>();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        readFile(lines);
        getYears(lines, years, yearsWithRanks);
        getRanks(lines, ranks);

        xAxis.setLabel("Year");
        xAxis.setLowerBound(years.get(0));
        xAxis.setUpperBound(years.get(years.size() - 1));
        xAxis.setMinorTickVisible(false);
        xAxis.setTickUnit(1);
        xAxis.setForceZeroInRange(false);

        yAxis.setLabel("Ranking");
        yAxis.setTickUnit(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        yAxis.setAutoRanging(false);
        yAxis.setMinorTickVisible(false);

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> chartSeries = new XYChart.Series<>();

        for (int i = 0; i < years.size(); i++) {
            chartSeries.getData().add(new XYChart.Data<>(years.get(i), ranks.get(i)));

        }

        lineChart.getData().add(chartSeries);

        // for (int i = 0; i < ranks.size(); i++) {

        // chartSeries.getData().add(new XYChart.Data<>(years.get(i), ranks.get(i)));
        // // System.out.println(years.get(i) + " " + ranks.get(i));
        // if (i == ranks.size() - 1) {
        // lineChart.getData().add(chartSeries);
        // }
        // }

        borderPane.setCenter(lineChart);

        window.setScene(scene);
        window.show();
    }

    public void readFile(List<String> lines) {
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader("data.txt"));
            buffRead.lines().forEach(line -> lines.add(line));
            buffRead.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getYears(List<String> lines, List<Integer> years, Map<Integer, Integer> yearsWithRanks) {
        lines.stream().map(line -> line.split(" ")[0])
                .forEach(year -> years.add(Integer.valueOf(year)));

        lines.stream().map(line -> line.split(" "))
                .forEach(year -> yearsWithRanks.put(Integer.valueOf(year[0]), Integer.valueOf(year[1])));
    }

    //

    public void getRanks(List<String> lines, List<Integer> ranks) {
        lines.stream().map(line -> line.split(" ")[1]).forEach(rank -> ranks.add(Integer.valueOf(rank)));
    }

    public Integer getLowestRank(List<Integer> ranks) {
        int lowest = 100;
        for (Integer rank : ranks) {
            if (lowest > rank) {
                lowest = rank;
            }

        }
        return lowest;

    }

    public Integer getHighestRank(List<Integer> ranks) {
        int highest = 100;
        for (Integer rank : ranks) {
            if (highest < rank) {
                highest = rank;
            }

        }
        return highest;

    }
}