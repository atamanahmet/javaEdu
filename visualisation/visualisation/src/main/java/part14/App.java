package part14;

import java.io.BufferedReader;
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
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(App.class);

    }

    public void start(Stage window) {
        List<String> lines = new ArrayList<>();
        List<Integer> years = new ArrayList<>();

        Map<String, Map<Integer, Double>> allDataMap = new HashMap<>();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        readFile(lines);
        createMap(lines, years, allDataMap);

        System.out.println(allDataMap);
        for (int i = 0; i < lines.size(); i++) {
            List<String> buffer = Arrays.asList(lines.get(i).split("\t"));
            for (String string : buffer) {
                if (string.matches("\\d{4}")) {
                    years.add(Integer.valueOf(string));
                }
            }
        }

        // Axis properties

        xAxis.setForceZeroInRange(false);
        xAxis.setLowerBound(years.get(0));
        xAxis.setUpperBound(years.get(years.size() - 1));
        xAxis.setTickUnit(1);
        xAxis.setMinorTickVisible(false);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();

        // for (Map.Entry<String, Map<Integer, Double>> value : allDataMap.entrySet()) {

        // // get party names as data label
        // value.getValue().entrySet().stream().forEach(pair -> {

        // // dataSeries.setName(value.getKey());
        // dataSeries.getData().add(new XYChart.Data<>(pair.getKey(), pair.getValue()));
        // lineChart.getData().add(dataSeries);

        // });

        // }

        Scene scene = new Scene(lineChart);
        window.setScene(scene);
        window.show();

    }

    public void readFile(List<String> lines) {
        try {
            BufferedReader buffread = new BufferedReader(new FileReader("partiesVotes.txt"));
            buffread.lines().forEach(line -> lines.add(line));
            buffread.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createMap(List<String> lines, List<Integer> years,
            Map<String, Map<Integer, Double>> allDataMap) {
        int index = 0;

        for (String line : lines) {
            Map<Integer, Double> yearsAndVotes = new HashMap<>();

            List<String> bufferArray = new ArrayList<>();
            String partyName = "";
            List<Double> votes = new ArrayList<>();

            bufferArray = Arrays.asList(line.split("\t"));

            for (int i = 0; i < bufferArray.size(); i++) {
                if (bufferArray.get(i).matches("\\D{2,4}")) {
                    partyName = bufferArray.get(i);
                    // System.out.println(bufferArray.get(i));
                } else if (bufferArray.get(i).matches("\\d{4}")) {

                    continue;

                } else if (bufferArray.get(i).matches("\\D+")) {

                    continue;

                } else if (bufferArray.get(i).equals("-")) {

                    votes.add(0.0);

                } else {
                    votes.add(Double.valueOf(bufferArray.get(i)));
                }

                if (votes.size() == years.size()) {
                    for (int j = 0; j < years.size(); j++) {

                        yearsAndVotes.put(years.get(j), votes.get(j));
                        System.out.println(yearsAndVotes);

                    }
                    if (index <= lines.size()) {
                        System.out.println(partyName);
                        allDataMap.put(partyName, yearsAndVotes);
                        index++;
                    }

                }

            }

        }

    }
}