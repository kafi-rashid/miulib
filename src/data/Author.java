package data;

import java.io.Serializable;

public class Author extends Person implements Serializable {
    public Author(String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
    }
}
