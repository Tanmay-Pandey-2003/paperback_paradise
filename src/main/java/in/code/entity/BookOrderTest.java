package in.code.entity;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import in.code.entity.Book_Order;

public class BookOrderTest {

    private Book_Order bookOrder;

    @Before
    public void setUp() {
        bookOrder = new Book_Order();
    }

    @Test
    public void testIdGetterAndSetter() {
        bookOrder.setId(1);
        assertEquals(1, bookOrder.getId());
    }

    @Test
    public void testOrderIdGetterAndSetter() {
        bookOrder.setOrderId("ORD123");
        assertEquals("ORD123", bookOrder.getOrderId());
    }

    // Similarly, write tests for other attributes

    @Test
    public void testToStringMethod() {
        bookOrder.setId(1);
        bookOrder.setOrderId("ORD123");
        bookOrder.setUserName("John Doe");
        bookOrder.setEmail("john.doe@example.com");
        bookOrder.setPhno("1234567890");
        bookOrder.setFulladd("123 Street, City");
        bookOrder.setAuthor("Jane Author");
        bookOrder.setPrice("$19.99");
        bookOrder.setBookName("Sample Book");
        bookOrder.setPaymentType("Credit Card");

        String expectedToString = "Book_Order [id=1, orderId=ORD123, userName=John Doe, email=john.doe@example.com, phno=1234567890, fulladd=123 Street, City, author=Jane Author, price=$19.99, bookName=Sample Book, paymentType=Credit Card]";
        assertEquals(expectedToString, bookOrder.toString());
    }
}
