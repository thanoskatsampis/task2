package task2.structures;

public class Member extends User{


    public Member(String name, int id, String contactDetails) {
        super(name, id, contactDetails);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name: " + super.getName() + '\'' +
                ", Id: " + super.getId() +
                ", contactDetails: " + super.getContactDetails() + '\'' +
                '}';
    }

}