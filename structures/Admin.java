package task2.structures;

public class Admin extends User {

    public Admin(String name, int id, String contactDetails) {
        super(name, id, contactDetails);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name: " + super.getName() + '\'' +
                ", Id: " + super.getId() +
                ", contactDetails: " + super.getContactDetails() + '\'' +
                '}';
    }
}
