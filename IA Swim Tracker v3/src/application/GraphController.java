package application;

import java.io.IOException;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphController extends FXMLManager {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    private static String course, event;
    private Swimmer instance;

    /*
     * Initialization method called when the controller is loaded.
     * Searches for Race objects matching course and event parameters.
     * Pulls those Race's times and dates, creating a graph and adding
     * those times and dates to it.
     */
    public void initialize(ActionEvent e) throws IOException {
        instance = Swimmer.getInstance("", 0, "");
        
        LinkedList<Race> specificEvent = instance.getSpecificEvents(course, event);
        
        LinkedList<String> times = new LinkedList<String>();
        LinkedList<String> dates = new LinkedList<String>();
        
        for(Race race : specificEvent) {
            times.add(race.getTime());
            dates.add(race.getDate());
        }
        
        x.setLabel("Date");
        y.setLabel("Time");

        lineChart.setTitle(course + " " + event);

        // Convert String time into seconds
        if (times != null && dates != null && times.size() == dates.size()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Times");

            for (int i = 0; i < times.size(); i++) {
                String[] timeParts = times.get(i).split(":");
                int minutes = Integer.parseInt(timeParts[0]);
                double seconds = Double.parseDouble(timeParts[1]);
                double totalSeconds = minutes * 60 + seconds;

                series.getData().add(new XYChart.Data<>(dates.get(i), totalSeconds));
            }

            lineChart.getData().add(series);
        }
    
    }

    // Method to switch to the main screen
    public void switchMain(ActionEvent e) throws IOException {
        super.switchMain(e);
    }
    
    // Method to set course and event information
    public void setInfo(String course, String event) {
        GraphController.course = course;
        GraphController.event = event;
    }
}
