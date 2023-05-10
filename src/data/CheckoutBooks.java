package data;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutBooks implements Serializable {

    public final Book book;
    public final Member member;
    public final LocalDate checkoutDate;
    public final LocalDate dueDate;

    public CheckoutBooks(Book book, Member member){
        this.book = book;
        this.member = member;
        this.checkoutDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(book.maxCheckoutDays);
    }

}
