package management;

import impl.model.Employee;
import impl.ManagementImpl;
import impl.model.Menu;
import impl.model.Restaurant;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Management {
    /*
    * Convert a JSON object to a String
    * @param fileName contains a String
    * @return a String from the JSON object
    * @throws IOException or ParseException
    */
    public String readFile(String fileName) throws IOException, ParseException;

    /*
    * Sets the name for the restaurant using the JSON file name attribute.
    * @param getName is used to run the readFile method to get a string name
    * @return a Restaurant object with the name gotten from the JSON file
    * @throws IOException or ParseException
    */
    public Restaurant setNameFromJSON(ManagementImpl manager) throws IOException, ParseException;

    public List<Menu> getMenuFromJSON(String jsonFile) throws FileNotFoundException;

    /*
    * Calculates the total price from a list of orders
    * @param order is used to create a List of Menu orders
    * @return a total price calculated from the order list
    */
    public int calculateTotalPrice(List<Menu> order);

    /*
    * Calculates the tip for the order from the method before
    * @param manager is used when running calculateTotalPrice. Here the manager gets a List<Menu>
    * @return the tip from the total price
    */
    public double calculateTipFromTotalPrice(List<Menu> order);

    /*
    * Shows the size of the List<Menu> of orders
    * @param order is used to create a List<Menu> of orders
    * @return the size of the order list
    */
    public int getOrderSize(List<Menu> order);

    /*
    * Calculates the salary per month for a Halftime employee
    * @param employee is used to calculate the salary per month from a given Employee
    * @return the salary for the given Employee
    */
    public double getSalaryPerMonth(Employee employee);

    /*
    * Search for an Employee by name from a list of employees
    * @param employeeList is a list of employees
    * @param nameSearch is the name to search for
    * @return the Employee the nameSearch matches
    */
    public Employee searchForEmployee(List<Employee> employeeList, String nameSearch);

    /*
    * Checks if a employee has enough work hours
    * @param employee takes an Employee to check the work hours
    * @return a boolean which is true if the employee got enough hours
    */
    public boolean enoughHours(Employee employee);
}
