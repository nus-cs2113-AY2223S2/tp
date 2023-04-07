package seedu.duke.venue;

public class Venue {

    private int venueIndex;
    private String venueName;

    private String venueLocation;

    private int venueCapacity;

    public Venue(int venueIndex, String venueName, String venueLocation, int venueCapacity){
        this.venueIndex = venueIndex;
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.venueCapacity = venueCapacity;
    }

    public String getVenueName(){
        return venueName;
    }

    public int getVenueIndex(){
        return venueIndex;
    }

    @Override
    public String toString() {
        return venueIndex + ". " +venueName + " " + venueLocation + " " + venueCapacity;
    }
}

