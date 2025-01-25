package data.visualisation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class App extends Application {
    // public static void main(String[] args) {
    // launch(App.class);
    // }

    public void start(Stage window) {
        List<String> lines = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        List<String> parties = new ArrayList<>();
        List<String> bufferArray;
        Map<String, Map<Integer, Double>> partiesMapWithVoteAndYears = new HashMap<>();

        readData(lines);

        bufferArray = Arrays.asList(lines.get(0).split("\t"));
        bufferArray.stream().filter(line -> line.matches("\\d{4}")).forEach(year -> years.add(Integer.valueOf(year)));

        for (int i = 1; i < lines.size(); i++) {
            bufferArray = Arrays.asList(lines.get(i).split("\t"));
            parties.add(bufferArray.get(0));
        }
        int index = 0;
        for (int i = 1; i < lines.size(); i++) {
            bufferArray = Arrays.asList(lines.get(i).split("\t"));
            List<Double> votes = new ArrayList<>();

            for (int j = 1; j < bufferArray.size(); j++) {
                if (bufferArray.get(j).equals("-")) {
                    votes.add(Double.valueOf(0));
                } else {
                    votes.add(Double.valueOf(bufferArray.get(j)));
                }
            }

            Map<Integer, Double> votesAndYears = new HashMap<>();
            for (int j = 0; j < years.size(); j++) {
                votesAndYears.put(years.get(j), votes.get(j));
            }
            System.out.println(votesAndYears);
            if (index < parties.size()) {
                partiesMapWithVoteAndYears.put(parties.get(index), votesAndYears);
                index++;
            }

        }

        System.out.println(partiesMapWithVoteAndYears);

        NumberAxis xAxis = new NumberAxis(Math.round(years.get(0)), (int) years.get(years.size() - 1), 1);
        NumberAxis yAxis = new NumberAxis();

        xAxis.setMinorTickVisible(false);
        xAxis.setTickLabelRotation(-90);

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            public String toString(Number object) {
                int value = object.intValue();
                if (years.contains(value)) {
                    return String.valueOf(value);
                }
                return "";
            }

            public Number fromString(String string) {
                return Integer.valueOf(string);
            }

        });
        yAxis.setTickUnit(1);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(30);

        xAxis.setLabel("Election Year");
        yAxis.setLabel("Vote (%)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Finland Election Chart 1968-2008");

        for (Map.Entry<String, Map<Integer, Double>> party : partiesMapWithVoteAndYears.entrySet()) {
            XYChart.Series<Number, Number> dataSet = new XYChart.Series<>();
            dataSet.setName(party.getKey());
            party.getValue().entrySet().stream().forEach(line -> {
                dataSet.getData().add(new XYChart.Data<>(line.getKey(), line.getValue()));
            });
            lineChart.getData().add(dataSet);
        }

        lineChart.setHorizontalGridLinesVisible(false);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(lineChart);
        // borderPane.setTop(new Label(lineChart.getTitle()));
        borderPane.setPadding(new Insets(10));
        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.show();
    }

    public void readData(List<String> lines) {
        try {
            BufferedReader buffread = new BufferedReader(new FileReader("data.txt"));
            buffread.lines().forEach(line -> lines.add(line));
            buffread.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}