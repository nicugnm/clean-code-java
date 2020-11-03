package videostore.horror;

import org.junit.Assert;
import org.junit.Test;


public class CustomerTest {

    @Test(expected = NullPointerException.class)
    public void throwsForNullMovieType() {
        new Movie("Star Wars",null);
    }
    @Test
    public void characterizationTest() {
        Customer customer = new Customer("John Doe");
        customer.addRental(new Movie("Star Wars", Movie.Type.NEW_RELEASE), 6);
        customer.addRental(new Movie("Sofia", Movie.Type.CHILDREN), 7);
        customer.addRental(new Movie("Inception", Movie.Type.REGULAR), 5);
        
        String expected = "Rental Record for John Doe\n"
                + "	Star Wars	18.0\n"
                + "	Sofia	7.5\n"
                + "	Inception	6.5\n"
                + "Amount owed is 32.0\n"
                + "You earned 4 frequent renter points";
        
        Assert.assertEquals(expected, customer.createStatement());
    }
}
