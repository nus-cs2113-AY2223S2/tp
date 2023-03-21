package entity;

public class Staff {
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
    private String workingDay;

    public Staff(String name, String workingDay, String dateOfBirth, String phoneNumber) {
        setName(name);
        setDateOfBirth(dateOfBirth);
        setWorkingDay(workingDay);
        setPhoneNumber(phoneNumber);
    }

    public String toString() {
        return this.name + ", " + "working in " + this.workingDay +
                ". Date of birth: " + this.dateOfBirth + ", phoneNumber: " + this.phoneNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getWorkingDay() {
        return this.workingDay;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public String getPhoneNumber() {
        return this.name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
