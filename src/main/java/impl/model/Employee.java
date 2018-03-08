package impl.model;

public class Employee {
    public enum Type {
        HALFTIME, FULLTIME
    }
    private String name;
    public Type type;
    private double salary;
    private int weekHours;

    public Employee(String name, Type type, double salary, int weekHours) {
        this.name = name;
        this.type = type;
        this.salary = salary;
        this.weekHours = weekHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWeekHours() {
        return weekHours;
    }

    public void setWeekHours(int weekHours) {
        this.weekHours = weekHours;
    }
}
