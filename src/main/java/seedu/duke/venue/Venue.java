package seedu.duke.venue;

public class Venue {
    private String venueName;

    private String venueLocation;

    private int venueCapacity;

    public Venue(String venueName, String venueLocation, int venueCapacity){
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.venueCapacity = venueCapacity;
    }

    public String getVenueName(){
        return venueName;
    }

    public String getVenueLocation(){
        return venueLocation;
    }

    public int getVenueCapacity(){
        return venueCapacity;
    }

    @Override
    public String toString() {
        return venueName + " " + venueLocation + " " + venueCapacity;
    }
}

