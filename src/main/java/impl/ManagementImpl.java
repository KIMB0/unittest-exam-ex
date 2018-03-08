package impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import impl.model.Employee;
import impl.model.Menu;
import impl.model.Restaurant;

import management.Management;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementImpl implements Management {
    public static final String FILENAME = "info.json";
    public List<Menu> orderList;
    Menu burger1 = new Menu("Burger", 30);
    Menu burger2 = new Menu("Cheese Burger", 35);
    Menu burger3 = new Menu("Bacon Burger", 35);
    Menu burger4 = new Menu("Bacon-Cheese Burger", 37);
    Menu burger5 = new Menu("Vegan Burger", 32);

    /*
    * This method is data-driven
    */
    public String readFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(fileName));
        JSONObject jsonObject = (JSONObject) object;
        Object name = jsonObject.get("name");
        return name.toString();
    }

    public Restaurant setNameFromJSON(ManagementImpl manager) throws IOException, ParseException {
        String name = manager.readFile(FILENAME);
        Restaurant restaurant = new Restaurant(name, "burger");
        return restaurant;
    }

    /*
    * This is a new method since assignment 1.
    */
    public List<Menu> getMenuFromJSON(String jsonFile) throws FileNotFoundException {
        JsonParser jsonParser = new JsonParser();
        List<Menu> listOfMenu = new ArrayList<>();

        JsonObject json = (JsonObject) jsonParser.parse(new FileReader(jsonFile));
        JsonArray jsonArray = json.get("Menu").getAsJsonArray();
        for (Object jsonObject : jsonArray) {
            JsonObject jsonMenu = (JsonObject) jsonObject;
            String name = jsonMenu.get("name").getAsString();
            int price = jsonMenu.get("price").getAsInt();

            Menu menu = new Menu(name, price);
            listOfMenu.add(menu);
        }
        return listOfMenu;
    }

    public int calculateTotalPrice(List<Menu> order) {
//        order = new ArrayList<Menu>();
//
//        order.add(burger1);
//        order.add(burger2);
//        order.add(burger4);

        int result = 0;
        for (Menu menu : order) {
            result = result + menu.getPrice();
        }
        return result;
    }

    public double calculateTipFromTotalPrice(List<Menu> order) {
        double result = calculateTotalPrice(order);
        result = result * 10 / 100;
        return result;
    }

    public int getOrderSize(List<Menu> order) {
        int size = order.size();
        return size;
    }

    public double getSalaryPerMonth(Employee employee) {
        double salary = employee.getSalary();

        if (employee.type == Employee.Type.HALFTIME) {
            double result = salary * employee.getWeekHours() * 4;
            System.out.println(employee.getName() + " gets " + result + "kr. per month");
            return result;
        }
        System.out.println(employee.getName() + " gets " + salary + "kr. per month");
        return salary;
    }

    public Employee searchForEmployee(List<Employee> employeeList, String nameSearch) {

        for (Employee employee : employeeList) {
            if (employee.getName() == nameSearch){
                return employee;
            }
        }
        throw new IllegalStateException(nameSearch + " could not be found in the system.");
    }

    public boolean enoughHours(Employee employee) {
        int workHours = employee.getWeekHours();
        if (workHours < 10) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, ParseException {
        ManagementImpl manager = new ManagementImpl();

        String name = manager.readFile(FILENAME);
        System.out.println("Method 1 result: " + name);

        Restaurant restaurant = manager.setNameFromJSON(manager);
        System.out.println("Method 2 result: " + restaurant.getName() + ". Type: " + restaurant.getType());

        List<Menu> listOfMenu = new ArrayList<>();
        listOfMenu = manager.getMenuFromJSON("burger.json");
        System.out.println("New method 1: Size og list: " + listOfMenu.size());

        List<Menu> order = new ArrayList<>();
        order.add(manager.burger1);
        order.add(manager.burger2);
        order.add(manager.burger4);
        int orderPirce = manager.calculateTotalPrice(order);
        System.out.println("Method 3 result: " + orderPirce + "kr. is the total price.");

        double tip = manager.calculateTipFromTotalPrice(order);
        System.out.println("Method 4 result: " + tip + "kr. is the total tip waiter will get.");

        int orderSize = manager.getOrderSize(order);
        System.out.println("Method 5 result: " + orderSize + " is the total size in this order.");

        Employee employee1 = new Employee("Billy Hofman", Employee.Type.HALFTIME, 110, 15);
        double result = manager.getSalaryPerMonth(employee1);
        System.out.println("Method 6 result: " + result + "kr. is the total salary per month.");

        List<Employee> employees = new ArrayList<Employee>();
        Employee employee2 = new Employee("Inger Henriksen", Employee.Type.FULLTIME, 25000, 37);
        Employee employee3 = new Employee("Niels A. Madsen", Employee.Type.FULLTIME, 25000, 37);
        Employee employee4 = new Employee("Charlie Conrad", Employee.Type.HALFTIME, 111.3, 20);
        Employee employee5 = new Employee("Sofie Madsen", Employee.Type.HALFTIME, 117.5, 21);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);

        Employee finalEmployee = manager.searchForEmployee(employees, "Sofie Madsen");
        System.out.println("Method 7 result: Name: " + finalEmployee.getName() + ". Type: " + finalEmployee.getType() + ". Salary: " + finalEmployee.getSalary() + "kr. Hours at week: " + finalEmployee.getWeekHours() + " hours.");

        boolean enoughHours = manager.enoughHours(employee1);
        System.out.println("Method 8 result: Has the employee enough hours: " + enoughHours);
    }
}
