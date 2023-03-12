package seedu.duke.data;

import java.util.ArrayList;

public abstract class RecordList {

    protected ArrayList<Record> recordList = new ArrayList<>();
    protected int recordCount = 0;

    public RecordList() {
        recordList = new ArrayList<>();
        recordCount = 0;
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void addRecord(Record record) {
        recordList.add(record);
        recordCount++;
    }

    public void printExpenseList() {
        for (int i = 1; i <= recordCount; i++) {
            Record record = recordList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }
}
