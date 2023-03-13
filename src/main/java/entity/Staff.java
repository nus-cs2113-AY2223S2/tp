package entity;

public class Staff {
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String workingDay;

    public Staff(String name, String dateOfBirth, String phoneNumber, String workingDay){
        setName(name);
        setDateOfBirth(dateOfBirth);
        setWorkingDay(workingDay);
        setPhoneNumber(phoneNumber);
    }

    public String toString(){
        return name + "," + "working in " + workingDay + ". Phone number: " + phoneNumber + ", Date of birth: " + dateOfBirth;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public String getName(){
        return this.name;
    }

    public String getDateOfBirth(){
        return this.dateOfBirth;
    }

    public String getWorkingDay(){
        return this.workingDay;
    }

    public String getPhoneNumber(){
        return this.name;
    }
}
