package functionalities;

public class Owner {
    protected String name;
    protected String contactNumber;

    public Owner(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return name + " | Contact Number: " + contactNumber;
    }

    @Override
    public boolean equals(Object o){
        Owner other = (Owner) o;
        return this.name.equals(other.name) && this.contactNumber.equals(other.contactNumber);
    }
    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
