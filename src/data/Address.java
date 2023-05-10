package data;

import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {

    public final String Street;
    public final String City;
    public final String State;
    public final String Zip;

    public Address(String street, String city, String State, String Zip){
        this.Street = street;
        this.City = city;
        this.State = State;
        this.Zip = Zip;
    }

    @Override
    public String toString(){
        return Street + "," + City + "," + State + "," + Zip;
    }

    @Override
    public boolean equals(Object addObj){
        var add = (Address) addObj;
        return Objects.equals(add.Street, Street) && Objects.equals(add.City, City) && Objects.equals(add.State, State) && Objects.equals(add.City, City);
    }
}
