package data;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class DataFacade {

    private static DataFacade dataFacade;

    List<User> users = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    List<CheckoutBooks> checkoutBooks = new ArrayList<>();

    private final String persistenceStorageLocation = "./src/data/storage";

    public static DataFacade getInstance() {
        if (dataFacade == null) {
            dataFacade = new DataFacade();
        }

        return dataFacade;
    }

    private DataFacade() {
        //persistence data
        loadChanges();

        //populate test data
        users.addAll(TestData.getTestUsers());
        if (addresses.size() < 1) {
            addresses.addAll(TestData.getAddresses());
        }
        if (authors.size() < 1) {
            authors.addAll(TestData.getAuthors());
        }
        if (books.size() < 1) {
            books.addAll(TestData.getBooks());
        }
        if (members.size() < 1) {
            members.addAll(TestData.getMembers());
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        saveChanges();
    }

    public void addMember(Member member) {
        this.members.add(member);
        saveChanges();
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
        saveChanges();
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        saveChanges();
    }

    public List<CheckoutBooks> getCheckoutBooks() {
        return checkoutBooks;
    }

    public void addCheckoutBook(int id, Book book, Member member) {
        this.checkoutBooks.add(new CheckoutBooks(book, member));
        saveChanges();
    }

    public void removeCheckout(CheckoutBooks selectedCheckout) {
        this.checkoutBooks.remove(selectedCheckout);
        saveChanges();
    }

    private void saveChanges() {

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream dbAddresses = new FileOutputStream(persistenceStorageLocation + "/libdb.addresses");
            ObjectOutputStream out = new ObjectOutputStream(dbAddresses);
            out.writeObject(addresses);
            out.close();
            dbAddresses.close();

            FileOutputStream dbMembers = new FileOutputStream(persistenceStorageLocation + "/libdb.members");
            ObjectOutputStream membersOut = new ObjectOutputStream(dbMembers);
            membersOut.writeObject(members);
            membersOut.close();
            dbMembers.close();

            FileOutputStream dbAuthor = new FileOutputStream(persistenceStorageLocation + "/libdb.authors");
            ObjectOutputStream authorOut = new ObjectOutputStream(dbAuthor);
            authorOut.writeObject(authors);
            authorOut.close();
            dbAuthor.close();

            FileOutputStream dbBooks = new FileOutputStream(persistenceStorageLocation + "/libdb.books");
            ObjectOutputStream booksOut = new ObjectOutputStream(dbBooks);
            booksOut.writeObject(books);
            booksOut.close();
            dbBooks.close();

            FileOutputStream dbCheckout = new FileOutputStream(persistenceStorageLocation + "/libdb.checkoutBooks");
            ObjectOutputStream checkoutOut = new ObjectOutputStream(dbCheckout);
            checkoutOut.writeObject(checkoutBooks);
            checkoutOut.close();
            dbCheckout.close();

        } catch (IOException ex) {
            System.out.println("IOException is caught " + ex.getMessage());
        }

    }

    public void loadChanges() {
        // Deserialization
        try {
            FileInputStream libAddress = new FileInputStream(persistenceStorageLocation + "/libdb.addresses");
            ObjectInputStream addressIn = new ObjectInputStream(libAddress);
            addresses = (List<Address>) addressIn.readObject();
            addressIn.close();
            libAddress.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            FileInputStream libMembers = new FileInputStream(persistenceStorageLocation + "/libdb.members");
            ObjectInputStream membersIn = new ObjectInputStream(libMembers);
            members = (List<Member>) membersIn.readObject();
            membersIn.close();
            libMembers.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            FileInputStream libAuth = new FileInputStream(persistenceStorageLocation + "/libdb.authors");
            ObjectInputStream authIn = new ObjectInputStream(libAuth);
            authors = (List<Author>) authIn.readObject();
            authIn.close();
            libAuth.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            FileInputStream libBooks = new FileInputStream(persistenceStorageLocation + "/libdb.books");
            ObjectInputStream bookIn = new ObjectInputStream(libBooks);
            books = (List<Book>) bookIn.readObject();
            bookIn.close();
            libBooks.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try {
            FileInputStream libCheckout = new FileInputStream(persistenceStorageLocation + "/libdb.checkoutBooks");
            ObjectInputStream checkoutIn = new ObjectInputStream(libCheckout);
            checkoutBooks = (List<CheckoutBooks>) checkoutIn.readObject();
            checkoutIn.close();
            libCheckout.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void uploadCopyOfBooks(Book selectedBook, int numberOfCopies) {
        selectedBook.setCopyOfBooks(numberOfCopies);
        saveChanges();
    }
}
