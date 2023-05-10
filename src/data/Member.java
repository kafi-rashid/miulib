package data;

import java.io.Serializable;

public class Member extends Person implements Serializable {
    public final int MemberId;


    public Member(int memberId, String firstName, String lastName, String phone, Address address){
        super(firstName, lastName, phone, address);
        this.MemberId = memberId;
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName();
    }
}
