package impl;

import impl.model.Employee;
import impl.model.Menu;
import impl.model.Restaurant;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ManagementImplTest {
    private final ManagementImpl management = new ManagementImpl();
    private List<Menu> menuList;
    Menu burger1 = new Menu("Burger", 30);
    Menu burger2 = new Menu("Cheese Burger", 35);
    Menu burger3 = new Menu("Bacon Burger", 35);
    Menu burger4 = new Menu("Bacon-Cheese Burger", 37);
    Menu burger5 = new Menu("Vegan Burger", 32);

    //UPDATE: used Hamcrest on this method. The method is also data-driven.
    @Test
    void readFile() throws IOException, ParseException {
        String name = management.readFile("info.json");
        String actual = name;
        String expected = "CPHB Grillen";
        assertThat(actual, is(expected));
    }

    //UPDATE: used Hamcrest on this method. The method is also data-driven.
    @Test
    void menuListNotEmpty() throws FileNotFoundException {
        menuList = management.getMenuFromJSON("burger.json");
        assertThat(menuList, not(nullValue()));
    }

    //UPDATE: used Hamcrest on this method. The method is also data-driven.
    @Test
    void checkStringFromMenuList() throws FileNotFoundException {
        menuList = management.getMenuFromJSON("burger.json");
        assertThat(menuList.get(0).getName(), is("Burger"));
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
        List<Menu> order = new ArrayList<>();
        order.add(burger1);
        order.add(burger2);
        order.add(burger4);
        double actual = management.calculateTipFromTotalPrice(order);
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