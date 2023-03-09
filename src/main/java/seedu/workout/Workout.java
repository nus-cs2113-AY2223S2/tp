package seedu.workout;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Workout {
    private Date date;

    public Workout(String dateString) throws Exception {
        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        // Parse the input dateString to create a Date object
        this.date = dateFormat.parse(dateString);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
