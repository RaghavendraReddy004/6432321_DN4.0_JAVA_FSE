class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Position: %s | Salary: $%.2f",
                employeeId, name, position, salary);
    }
}

public class EmployeeManagementSystem {

    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    // Add employee to array
    public boolean addEmployee(Employee employee) {
        if (count >= employees.length) {
            System.out.println("Error: Employee array is full. Cannot add more employees.");
            return false;
        }
        employees[count++] = employee;
        System.out.println("Added: " + employee);
        return true;
    }

    // Search employee by ID
    public Employee searchEmployeeById(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse and display all employees
    public void traverseEmployees() {
        if (count == 0) {
            System.out.println("No employees to display.");
            return;
        }
        System.out.println("Employee List:");
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete employee by ID
    public boolean deleteEmployeeById(int employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                System.out.println("Deleting: " + employees[i]);
                // Shift all elements left to fill gap
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null;
                count--;
                return true;
            }
        }
        System.out.println("Employee with ID " + employeeId + " not found.");
        return false;
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem(5);

        System.out.println("Adding Employees...");
        system.addEmployee(new Employee(101, "Alice", "Manager", 75000));
        system.addEmployee(new Employee(102, "Bob", "Developer", 60000));
        system.addEmployee(new Employee(103, "Charlie", "Designer", 55000));
        system.addEmployee(new Employee(104, "Diana", "QA Engineer", 50000));

        System.out.println("\nTraversing Employees:");
        system.traverseEmployees();

        System.out.println("\nSearching for Employee with ID 103:");
        Employee emp = system.searchEmployeeById(103);
        if (emp != null) {
            System.out.println("Found: " + emp);
        } else {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting Employee with ID 102:");
        system.deleteEmployeeById(102);

        System.out.println("\nTraversing Employees after deletion:");
        system.traverseEmployees();

        System.out.println("\nAttempting to add Employee beyond capacity:");
        system.addEmployee(new Employee(105, "Ethan", "Support", 45000));
        system.addEmployee(new Employee(106, "Fiona", "Intern", 30000)); // Should fail because capacity is 5
    }
}