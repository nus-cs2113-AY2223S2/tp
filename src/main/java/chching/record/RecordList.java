package chching.record;

import java.util.ArrayList;

public abstract class RecordList {

    protected ArrayList<Record> recordList = new ArrayList<>();
    private double balance;
    

    public RecordList() {
        recordList = new ArrayList<>();
    }

    public ArrayList<Record> getRecordList() {
        return recordList;
    }

    public int getRecordCount() {
        return recordList.size();
    }

    public int getBalance(){
        for(int i = 0; i < getRecordCount(); i++){
            balance += this.recordList.get(i).value;
        }
        return balance;
    }

    public void addRecord(Record record) {
        recordList.add(record);
    }

    public void printRecordList() {
        for (int i = 1; i <= getRecordCount(); i++) {
            Record record = recordList.get(i - 1);
            System.out.println(i + ". " + record.toString());
        }
    }

    public Record get(int index) {
        return recordList.get(index);
    }
}
