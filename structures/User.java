package task2.structures;

import java.util.Objects;

public abstract class User {
    private String name;
    private int Id;
    private String contactDetails;

    public User(String name, int id, String contactDetails) {
        this.name = name;
        this.Id = id;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Id == user.Id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}