import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(int id, String name, double salary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added: ID=" + id + ", Name=" + name + ", Salary=" + salary);
    }

    public void updateEmployee(int id, double newSalary) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                emp.salary = newSalary;
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    public void removeEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                employees.remove(emp);
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    public void searchEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.id == id) {
                System.out.println("Employee Found: ID=" + emp.id + ", Name=" + emp.name + ", Salary=" + emp.salary);
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.id + ", Name: " + emp.name + ", Salary: " + emp.salary);
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        Scanner scanner = new Scanner(System.in);

        
        ems.displayEmployees(); 
        ems.addEmployee(101, "Anish", 50000);
        ems.addEmployee(102, "Bobby", 60000);
        ems.updateEmployee(101, 55000);
        ems.searchEmployeeById(102);
        ems.removeEmployee(101);
        ems.displayEmployees();
        ems.addEmployee(101, "Charlie", 70000);
    }
}
