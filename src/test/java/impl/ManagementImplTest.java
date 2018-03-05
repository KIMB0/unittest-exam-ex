package impl;

import management.Management;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagementImplTest {
    private final ManagementImpl management = new ManagementImpl();
    private List<Menu> orderList;
    Menu burger1 = new Menu("Burger", 30);
    Menu burger2 = new Menu("Cheese Burger", 35);
    Menu burger3 = new Menu("Bacon Burger", 35);
    Menu burger4 = new Menu("Bacon-Cheese Burger", 37);
    Menu burger5 = new Menu("Vegan Burger", 32);

    @Test
    void readFile() throws IOException, ParseException {
        String name = management.readFile("info.json");
        String actual = name;
        String expected = "CPHB Grillen";
        assertEquals(expected, actual);
    }

    @Test
    void setNameFromJSON() throws IOException, ParseException {
        String name = management.readFile("info.json");
        Restaurant restaurant = new Restaurant(name, "Burger");
        String actual = restaurant.getName();
        String expected = "CPHB Grillen";
        assertEquals(expected, actual);
    }

    @Test
    void calculateTotalPrice() {
        List<Menu> order = new ArrayList<>();
        order.add(burger1);
        order.add(burger2);
        order.add(burger4);

        int actual = management.calculateTotalPrice(order);
        int expected = 102;
        assertEquals(expected, actual);
    }

    @Test
    void calculateTipFromTotalPrice() {
        double actual = management.calculateTipFromTotalPrice(management);
        double expected = 10.2;
        assertEquals(expected, actual);
    }

    @Test
    void getOrderSize() {
        List<Menu> order = new ArrayList<>();
        order.add(burger1);
        order.add(burger2);
        order.add(burger4);
        order.add(burger5);
        int actual = management.getOrderSize(order);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void getSalaryPerMonth() {
        Employee employee1 = new Employee("Billy Hofman", Employee.Type.HALFTIME, 110, 15);
        double actual = management.getSalaryPerMonth(employee1);
        double expected = 6600;
        assertEquals(expected, actual);

        Employee employee2 = new Employee("Pia Andersen", Employee.Type.FULLTIME, 25000, 37);
        double actual2 = management.getSalaryPerMonth(employee2);
        double expected2 = 25000;
        assertEquals(expected2, actual2);
    }

    @Test
    void searchForEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Inger Henriksen", Employee.Type.FULLTIME, 25000, 37));
        employeeList.add(new Employee("Niels A. Madsen", Employee.Type.FULLTIME, 25000, 37));
        employeeList.add(new Employee("Charlie Conrad", Employee.Type.HALFTIME, 111.3, 20));
        employeeList.add(new Employee("Sofie Madsen", Employee.Type.HALFTIME, 117.5, 21));

        String nameSearch = "Sofie Madsen";
        Employee actual = management.searchForEmployee(employeeList, nameSearch);
        Employee expected = employeeList.get(3);
        assertEquals(expected, actual);
    }

    @Test
    void enoughHours() {
        Employee employee = new Employee("Hans Christian", Employee.Type.HALFTIME, 110, 10);
        boolean actual = management.enoughHours(employee);
        boolean expected = true;
        assertEquals(expected, actual);

        Employee employee2 = new Employee("Søren Christian", Employee.Type.HALFTIME, 110, 9);
        boolean actual2 = management.enoughHours(employee2);
        boolean expected2 = false;
        assertEquals(expected2, actual2);
    }
}