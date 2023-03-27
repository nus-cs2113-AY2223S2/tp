package seedu;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public Date date;

    public DateFormat(Date date) {
        this.date = date;
    }

    public String formatDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.date);
    }
}

