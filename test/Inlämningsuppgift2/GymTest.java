package Inlämningsuppgift2;

import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GymTest {

    Customer c1 = new Customer("Leon Wass", "9803102913", LocalDate.now());
    Customer c2 = new Customer("Leon Vallin", "9803102913", LocalDate.parse("2015-01-30"));
    Gym gym = new Gym();
    List<Customer> customerList = new ArrayList<>();
    String file = "C:\\Users\\Leon_\\IdeaProjects\\Inlämningsuppgift 2 OOP\\src\\Customers.txt";

    @Test
    public void activeMemberTest() {
        assertTrue(gym.activeMember(c1));
        assertFalse(gym.activeMember(c2));
    }

    @Test
    public void searchCustomerTest() throws NullPointerException {
        customerList.add(c1);
        customerList.add(c2);
        Customer c1 = Gym.searchCustomer("Leon Wass", customerList);
        Customer c2 = Gym.searchCustomer("9803102913", customerList);
        Customer c3 = Gym.searchCustomer("Leon", customerList);
        Customer c4 = Gym.searchCustomer("980310", customerList);

        assertTrue(c1.getName().equalsIgnoreCase("Leon Wass"));
        assertTrue(c1.getPersonNr().equalsIgnoreCase("9803102913"));
        assertTrue(c2.getName().equalsIgnoreCase("Leon Vallin"));
        assertTrue(c2.getPersonNr().equalsIgnoreCase("9803102913"));
        assertNull(c3);
        assertNull(c4);

    }

    @Test
    void readFileTest() {
        List<Customer> gymCustomer = Gym.readFile();
        assertNotNull(gymCustomer);
        assertTrue(gymCustomer.size() > 0);
        for (Customer customer : gymCustomer) {
            assertNotNull(customer.getName());
            assertNotNull(customer.getPersonNr());
            assertNotNull(customer.getGymFee());
        }
    }
}
