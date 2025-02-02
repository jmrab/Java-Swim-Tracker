package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Swimmer {
    private static Swimmer instance; // Singleton instance
    private String name, gender;
    private int age;
    private LinkedList<Race> swimData;
    
    // Private constructor for singleton pattern
    private Swimmer(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Get the Singleton instance of Swimmer.
     *
     * @param name   Swimmer's name
     * @param age    Swimmer's age
     * @param gender Swimmer's gender
     * @return Singleton instance of Swimmer
     */
    public static Swimmer getInstance(String name, int age, String gender) {
        if (instance == null) {
            instance = new Swimmer(name, age, gender);
        } 
        return instance;
    }
    
    // Get methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public LinkedList<Race> getSwimData() {
        return swimData;
    }
    
    /**
     * Get specific events for a given course and event type.
     * 
     * @param course Course type
     * @param event  Event type
     * @return specificEvents List of specific events
     */
    public LinkedList<Race> getSpecificEvents(String course, String event) {
        LinkedList<Race> specificEvents = new LinkedList<>();

        if(swimData != null) {
            for (Race race : swimData) {
                if(race.getCourse().equals(course) && race.getEvent().equals(event)) {
                    specificEvents.add(race);
                }
            }
        }
        return specificEvents;
    }
    
    // Set methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * Create a new Race from inputed swim information
     * 
     * @param event		Event type
     * @param course	Course type
     * @param time   	Race time
     * @param date      Race date
     * @param variables Array of variables related to the race
     * @param sentiment Swimmer's sentiment after the race
     */
    public void inputSwimData(String event, String course, String time, String date, String[] variables, String sentiment) {
        if(swimData == null) {
            swimData = new LinkedList<>();
        }
        swimData.add(new Race(event, course, time, date, variables, sentiment));
    }
    
    /**
     * Export swimmer information and race information to a file.
     *
     * @param filePath Path of the file to export data
     * @throws IOException If an error occurs while writing the file
     */
    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write signature
            writer.write("SwimmerExportFile"); // Signature to identify exported files
            writer.newLine();
            
            // Write swimmer information
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Age: " + age);
            writer.newLine();
            writer.write("Gender: " + gender);
            writer.newLine();

            // Write race information
            if (swimData != null) {
                for (Race race : swimData) {
                    writer.write("Race:");
                    writer.newLine();
                    writer.write("  Time: " + race.getTime());
                    writer.newLine();
                    writer.write("  Event: " + race.getEvent());
                    writer.newLine();
                    writer.write("  Date: " + race.getDate());
                    writer.newLine();
                    writer.write("  Feelings Before: " + Arrays.toString(race.getVariables()));
                    writer.newLine();
                    writer.write("  Feeling After: " + race.getSentiment());
                    writer.newLine();
                    writer.write("  Course: " + race.getCourse());
                    writer.newLine();
                    writer.write("  Race Type: " + race.getRace());
                    writer.newLine();
                    writer.newLine(); 
                }
            }
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Error importing data: " + e.getMessage(), "Import Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Import swimmer information and race information from a file.
     *
     * @param filePath Path of the file to import data
     * @throws IOException If an error occurs while reading the file
     * @returns boolean Indicating success of import
     */
    public boolean importData(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Check for signature
            String signature = reader.readLine();
            if (!"SwimmerExportFile".equals(signature)) {
                JOptionPane.showMessageDialog(null, "Error importing data: Not a valid exported file.", "Import Error", JOptionPane.ERROR_MESSAGE);
                return false; // Indicate failure
            }
            
            // Initialize swimData if null
            if (swimData == null) {
                swimData = new LinkedList<>();
            }
            
            // Proceed with importing data
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    this.name = line.substring(6).trim();
                } else if (line.startsWith("Age: ")) {
                    this.age = Integer.parseInt(line.substring(5).trim());
                } else if (line.startsWith("Gender: ")) {
                    this.gender = line.substring(8).trim();
                } else if (line.equals("Race:")) {
                    String time = reader.readLine().substring(8).trim();
                    String event = reader.readLine().substring(9).trim();
                    String date = reader.readLine().substring(8).trim();
                    String feelingsBeforeStr = reader.readLine().substring(18).trim();
                    String[] feelingsBefore = feelingsBeforeStr.substring(1, feelingsBeforeStr.length() - 1).split(", ");
                    String feelingAfter = reader.readLine().substring(16).trim();
                    String course = reader.readLine().substring(9).trim();
                    reader.readLine();
                    Race race = new Race(event, course, time, date, feelingsBefore, feelingAfter);
                    swimData.add(race);
                }
            }
            return true; 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error importing data: " + e.getMessage(), "Import Error", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
    }

}