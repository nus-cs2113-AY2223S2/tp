package chching.record;

import java.util.ArrayList;

public abstract class RecordList {

    protected ArrayList<Record> recordList = new ArrayList<>();
    protected int recordCount = 0;
    private double balance;
    

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

    public double getBalance(){

        for(int i = 0; i < recordCount; i++){
            balance+= recordList.get(i).value;
        }
        return balance;
    }

    public void addRecord(Record record) {
        recordList.add(record);
        recordCount++;
    }

    public void printRecordList() {
        for (int i = 1; i <= recordCount; i++) {
            Record record = recordList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }

    public Record get(int index) {
        return recordList.get(index);
    }
}
