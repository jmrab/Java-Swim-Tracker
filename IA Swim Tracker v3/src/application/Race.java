package application;

import java.util.Arrays;

public class Race {
	
	private String time, event, date, sentiment, course, race;
	private String[] variables;
	private double percentImprovement;
    private double timeDropped;
	
    // Race constructor
	public Race(String event, String course, String time, String date, String[] variables, String sentiment) {
		this.time = time;
		this.event = event;
		this.date = date;
		this.variables = variables;
		this.sentiment = sentiment;
		this.course = course;
		race = event.substring(0,3).trim() + " " + course + " " + event.substring(3);
		this.percentImprovement = 0.0;
	    this.timeDropped = 0.0;
	}
	
	// Turn a time in String format to seconds
	public double getRaceTimeInSeconds() {
		String[] parts = time.split(":|\\.");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        int deciSeconds = Integer.parseInt(parts[2]);

        double totalSeconds = minutes * 60 + seconds + deciSeconds / 100.0;
        return totalSeconds;
	}
	
	// Get methods
	public String getTime() {
		return time;
	}
	
	public String getEvent() {
		return event;
	}
	
	public String getDate() {
		return date;
	}
	
	public String[] getVariables() {
		return variables;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	
	public String getCourse() {
		return course;
	}
 
	public String getRace() {
		return race;
		
	}
	
	public String getFeelingsBeforeAsString() {
		return String.join(", ", variables);
    }
	
	public double getPercentImprovement() {
		return percentImprovement;
	}

	public double getTimeDropped() {
        return timeDropped;
    }
	    
	// Set methods
	public void setPercentImprovement(double percentImprovement) {
        this.percentImprovement = percentImprovement;
    }

    public void setTimeDropped(double timeDropped) {
        this.timeDropped = timeDropped;
    }
    
    // toString method for testing
    public String toString() {
        return "Race{" +
                "time='" + time + '\'' +
                ", event='" + event + '\'' +
                ", date='" + date + '\'' +
                ", sentiment='" + sentiment + '\'' +
                ", course='" + course + '\'' +
                ", race='" + race + '\'' +
                ", variables=" + Arrays.toString(variables) +
                ", percentImprovement=" + percentImprovement +
                ", timeDropped=" + timeDropped +
                '}';
    }
}