package seedu.duke.data;

import java.util.ArrayList;

public class RecordList {

    protected static ArrayList<Record> recordList = new ArrayList<>();
    protected static int recordCount = 0;
    private int balance;
    

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

    public int getBalance(){
        for(int i = 0; i<this.recordCount; i++){
            balance+= this.recordList.get(i).value;
        }
        return balance;
    }

    public static void addRecord(Record record) {
        recordList.add(record);
        recordCount++;
    }

    public static void printRecordList() {
        for (int i = 1; i <= recordCount; i++) {
            Record record = recordList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }
}
