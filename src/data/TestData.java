package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestData {

    public static List<User> getTestUsers(){
        var tmpUsers = new ArrayList<User>();
        tmpUsers.add(new User("james" ,"abc123", "James", "Torantneeno", UserRoles.Admin));
        tmpUsers.add(new User("pj" ,"abc123", "Pasindu", "J", UserRoles.Both));
        tmpUsers.add(new User("ponchanon" ,"abc123", "Ponchanon", "Rone", UserRoles.Both));
        tmpUsers.add(new User("lib1" ,"abc123", "Library", "01", UserRoles.Librarian));
        tmpUsers.add(new User("lib2" ,"abc123", "Library", "02", UserRoles.Librarian));
        tmpUsers.add(new User("iqbal" ,"abc123", "Md", "Iqbal", UserRoles.Librarian));
        tmpUsers.add(new User("kafi" ,"abc123", "Kafi", "Gordan", UserRoles.Librarian));
        tmpUsers.add(new User("joanna" ,"abc123", "Joanna", "Saleem", UserRoles.Both));

        return tmpUsers;
    }

    public static List<Address> getAddresses(){
        var tmpAddresses = new ArrayList<Address>();
        tmpAddresses.add(new Address("1000 n", "4th st", "IA", "52556"));
        tmpAddresses.add(new Address("100 n", "main st", "IA", "52557"));
        tmpAddresses.add(new Address("58", "4th st", "Randolph, MA", "02368"));
        tmpAddresses.add(new Address("7361", "Ann Street", "Mundelein, IL", "60060"));
        tmpAddresses.add(new Address("30", "Orange Street", "Union, NJ", "07083"));
        return tmpAddresses;
    };

    public static List<Author> getAuthors(){
        var tmpAuthors = new ArrayList<Author>();
        tmpAuthors.add(new Author("James", "Gordon", "456 56454 545", getAddresses().get(1)));
        tmpAuthors.add(new Author("Kiyanu", "Reevs", "344 43434 545", getAddresses().get(2)));
        return tmpAuthors;
    }

    public static List<Book> getBooks() {
        var tmpBooks = new ArrayList<Book>();
        tmpBooks.add(new Book(1, "Programming", "2222222", getAuthors().get(1), 2, 7));
        tmpBooks.add(new Book(2, "System Design", "3456733456", getAuthors().get(1), 1, 21));
        tmpBooks.add(new Book(3, "STC", "1111111", getAuthors().get(1), 0, 7));
        tmpBooks.add(new Book(4, "Consciousness", "3333333", getAuthors().get(1), 1, 7));
        return tmpBooks;
    }

    public static List<Member> getMembers() {
        var tmpMembers = new ArrayList<Member>();
        tmpMembers.add(new Member(1, "James", "Gordan", "3343 4343", getAddresses().get(1)));
        tmpMembers.add(new Member(2, "Pasindu", "j", "1426 9765", getAddresses().get(1)));
        tmpMembers.add(new Member(3, "Ponchanon", "Rone", "1426 9645", getAddresses().get(1)));
        tmpMembers.add(new Member(4, "Library", " 01", "1326 9545", getAddresses().get(1)));
        tmpMembers.add(new Member(5, "Library", "02", "1316 9645", getAddresses().get(1)));
        tmpMembers.add(new Member(6, "Md", "Iqbal", "1325 3645", getAddresses().get(1)));
        tmpMembers.add(new Member(7, "kafi", "Gordan", "1326 9645", getAddresses().get(1)));
        tmpMembers.add(new Member(8, "Joanna", "Saleem", "1726 9645", getAddresses().get(1)));
        return tmpMembers;
    }
}
