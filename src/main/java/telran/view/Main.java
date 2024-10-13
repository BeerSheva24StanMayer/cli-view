package telran.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;

record Employee(long id, String name, String department, int salary, LocalDate birthDate) {
}

public class Main {
    static InputOutput io = new StandardInputOutput();
    /*********************** */
    // For HW #35 constants
    final static int MIN_SALARY = 5000;
    final static int MAX_SALARY = 30000;
    final static String[] DEPARTMENTS = { "QA", "Audit", "Development", "Management" };
    // name should be at least 3 English letters; first - capital, others - lower
    // case
    final static long MIN_ID = 100000;
    final static long MAX_ID = 999999;
    final static int MIN_AGE = 18;
    final static int MAX_AGE = 70;

    /**
     * @throws Exception *********************************
     */
    public static void main(String[] args) throws Exception {
        // readEmployeeAsObject();
        readEmployeeBySeparateFields();

    }

    static void readEmployeeAsObject() {
        Employee empl = io.readObject("Enter employee data in the format:" +
                " <id>#<name>#<department>#<salary>#<yyyy-MM-DD> ",
                "Wrong format for Employee data", str -> {
                    String[] tokens = str.split("#");
                    return new Employee(Long.parseLong(tokens[0]), tokens[1], tokens[2],
                            Integer.parseInt(tokens[3]), LocalDate.parse(tokens[4]));

                });
        io.writeLine("You entered the following Employee data");
        io.writeLine(empl);
    }

    static void readEmployeeBySeparateFields() throws Exception {
        // TODO
        // Enter ID, Enter name, Enter department, Enter salary, Enter birthdate
        long id = io.readNumberRange("Enter employee ID in range between " + MIN_ID + " and " + MAX_ID + ":",
                "Invalid ID, must be a number between " + MIN_ID + " and " + MAX_ID, MIN_ID, MAX_ID).longValue();

        String name = io.readStringPredicate(
                "Enter employee name: should include at least 3 latin letters and should start with capital letter",
                "Incorrect name due to conditions", s -> s.matches("^[A-Z][a-z]{2,}$"));

        String department = io.readStringOptions("Enter company department",
                "Department doesn't exist in company", new HashSet<>(Arrays.asList(DEPARTMENTS)));

        int salary = io.readNumberRange("Enter the employee salary:", 
        "The salary is out of range of company salary limit", MIN_SALARY, MAX_SALARY).intValue();

        LocalDate birthDate = io.readIsoDateRange("Enter date of birth of employee in format yyyy-MM-DD",
         "Wrong age, should be between " + MIN_AGE + " and " + MAX_AGE, 
         LocalDate.of((LocalDate.now().getYear() - MAX_AGE), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), 
         LocalDate.of((LocalDate.now().getYear() - MIN_AGE), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));

        Employee employee = new Employee(id, name, department, salary, birthDate);
        io.writeLine("You entered the following Employee data");
        io.writeLine(employee);

    }
}