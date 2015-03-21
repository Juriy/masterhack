package model;

/**
 * Created by Juriy on 3/21/2015.
 */
public class Event {

    private long timestamp;
    private String eventType;
    private double amount;

    public Event() {
    }

    public Event(long timestamp, String eventType, double amount) {
        this.timestamp = timestamp;
        this.eventType = eventType;
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
