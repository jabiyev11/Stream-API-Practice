import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class TaskSolutions {

    private static final List<Employee> employeeList = Data.getData();

    private static void employeesAgeGreaterThan30() {


        List<Employee> employeesAgeGreaterThan30 = employeeList
                .stream()
                .filter(employee -> employee.getAge() > 30)
                .toList();

        employeesAgeGreaterThan30.forEach(System.out::println);
    }

    private static void listOfEmployeeNames() {

        List<String> employeeNames = employeeList
                .stream()
                .map(Employee::getName)
                .toList();

        employeeNames.forEach(System.out::println);
    }

    private static void employeeWithHighestSalary() {
        Optional<Employee> highestPaidEmployee = employeeList
                .stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        highestPaidEmployee.ifPresent(employee -> System.out.println("Employee with highest salary: "
                + employee));
    }

    private static void calculateAverageSalary() {

        Double averageSalary = employeeList
                .stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(averageSalary);

    }

    private static void employeesSortedBySurname() {
        List<Employee> sortedBySurname = employeeList
                .stream()
                .sorted(Comparator.comparing(Employee::getSurname))
                .toList();

        sortedBySurname.forEach(System.out::println);
    }

    private static void isAllOlderThan25() {

        boolean isOlderThan25 = employeeList
                .stream()
                .allMatch(employee -> employee.getAge() > 25);

        System.out.println(isOlderThan25);
    }

    private static void nameOfEmployeesInIT() {

        List<String> employeesInIT = employeeList
                .stream()
                .filter(employee -> Department.IT.equals(employee.getDepartment()))
                .map(Employee::getName)
                .toList();

        employeesInIT.forEach(System.out::println);
    }

    private static void groupByDepartment() {
        Map<Department, List<Employee>> departmentToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployees.forEach((department, employees) -> {
            System.out.println("Department: " + department);
            employees.forEach(employee -> System.out.println("\t " + employee));
        });
    }

    private static void countOfEmployeesByDepartment() {

        Map<Department, List<Employee>> departmentToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployees.forEach((department, employees) -> {
            System.out.println("Department: " + department);
            System.out.println("Count: " + employees.size());
        });
    }

    private static void averageAgePerDepartment() {

        Map<Department, List<Employee>> departmentToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployees.forEach((department, employees) -> {
            System.out.println("Department: " + department);
            System.out.println("Average age: " + employees
                    .stream()
                    .collect(Collectors.averagingInt(Employee::getAge))
                    + "\n-----------------------------------------------------------");
        });
    }

    private static void partitionEmployersAndEmployees() {

        Map<Boolean, List<Employee>> employerToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::isEmployer));

        employerToEmployees.forEach((isEmployer, employees) -> {
            System.out.println("----------------------------------------------------------------------------------------------------------------------");
            if (isEmployer)
                System.out.println("Employer: ");
            else
                System.out.println("Employee: ");
            employees.forEach(System.out::println);
        });
    }

    private static void getTotalSalary() {

        double sumOfSalaries = employeeList
                .stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println(sumOfSalaries);
    }

    private static void employeeWithLowestSalary() {

        Optional<Employee> lowestPaidEmployee = employeeList
                .stream()
                .min(Comparator.comparingDouble(Employee::getSalary));

        lowestPaidEmployee.ifPresent(employee -> System.out.println("Employee with lowest salary: "
                + employee));
    }


    private static void listOfUniqueSurnames() {

        List<String> uniqueSurnames = employeeList
                .stream()
                .map(Employee::getSurname)
                .distinct()
                .toList();

        uniqueSurnames.forEach(System.out::println);
    }

    private static void groupEmployeeNameToSalary() {

        Map<String, List<Employee>> nameToSalary = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getName));

        nameToSalary.forEach((name, employees) -> {
            System.out.println("Name: " + name);
            employees.forEach(employee -> System.out.println("Salary: " + employee.getSalary()));
            System.out.println("\n------------------------------------------------------");
        });
    }

    private static void notEmployers() {

        Map<Boolean, List<Employee>> isEmployerOrNot = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::isEmployer));

        isEmployerOrNot.forEach((isEmployer, employees) -> {
            if (!isEmployer) {
                System.out.println("Non-Employers");
                employees.forEach(employee -> System.out.println(employee.getName()));
            }
        });
    }

    private static void getFullName() {

        List<String> fullNames = employeeList
                .stream()
                .map(employee -> employee.getName() + " " + employee.getSurname())
                .toList();


        fullNames.forEach(System.out::println);
    }

    private static void updateSalaryBy10Percent() {
        employeeList
                .forEach(employee -> employee.setSalary(employee.getSalary() * 1.10));

        employeeList.forEach(System.out::println);
    }

    private static void salarySumOfEmployeesOfIT() {

        Map<Department, List<Employee>> departmentToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployees.forEach((department, employees) -> {
            if (Department.IT.equals(department)) {
                System.out.println("Salary Sum: ");
                double sumOfITSalaries = employees
                        .stream()
                        .mapToDouble(Employee::getSalary)
                        .sum();
                System.out.println(sumOfITSalaries);
            }
        });
    }

    private static void sumOfSalariesPerDepartment() {

        Map<Department, List<Employee>> departmentToEmployee = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployee.forEach((department, employees) -> {
            System.out.println("Department: " + department);
            double sumPerDepartment = employees
                    .stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            System.out.println("Total Salary: " + sumPerDepartment);
            System.out.println("\n------------------------------------------");
        });
    }

    private static void employeesWithSalaryGreaterThanAverage() {

        Double averageSalary = employeeList
                .stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        employeeList
                .forEach(employee -> {
                    if (employee.getSalary() > averageSalary) {
                        System.out.println(employee);
                    }
                });
    }

    private static void employeeListToMap() {

        Map<Integer, List<Employee>> idToEmployee = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getId));

        Set<Map.Entry<Integer, List<Employee>>> entries = idToEmployee.entrySet();
        entries.forEach((employeeID) -> System.out.println("Employee: " + employeeID));
    }

    private static void namesBySortedSalary() {

        List<String> namesSortedBySalary = employeeList
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::getName)
                .toList();

        System.out.println(namesSortedBySalary);
    }

    private static void findOldestEmployeePerDepartment() {
        Map<Department, List<Employee>> departmentToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentToEmployees.forEach(((department, employees) -> {
            System.out.println("Department: " + department);
            Optional<Employee> oldestEmployee = employees
                    .stream()
                    .max(Comparator.comparing(Employee::getAge));
            oldestEmployee.ifPresent(employee -> System.out.println(oldestEmployee.get()));
            System.out.println("\n-----------------------------------");
        }));
    }

    private static void mergeNameAndSurname() {

        List<String> concatenatedNameAndSurname = employeeList
                .stream()
                .map(employee -> employee.getName() + ", " + employee.getSurname())
                .toList();

        concatenatedNameAndSurname.forEach(System.out::println);
    }

    private static void flattenListOfList() {

        Map<Department, List<Employee>> departmentToEmployee = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        Collection<List<Employee>> listOfEmployeeListByDepartment = departmentToEmployee.values();

        listOfEmployeeListByDepartment.forEach(System.out::println);
    }

    private static void yearsOfExperience(){

        List<Integer> listOfExperienceYears = employeeList
                .stream()
                .map(employee -> employee.getStartDate().until(LocalDate.now()).getYears())
                .toList();

        listOfExperienceYears.forEach(System.out::println);
    }

    private static void findLongestServingEmployee(){

        Optional<Employee> longestServingEmployee = employeeList
                .stream()
                .max(Comparator.comparingInt(e -> Period.between(e.getStartDate(), LocalDate.now()).getYears()));

        longestServingEmployee.ifPresent(System.out::println);
    }

    private static void combinedSalaryOfEmployees(){

        Double totalSalary = employeeList
                .stream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);

        System.out.println(totalSalary);
    }

    private static void numberOfEmployeesPerPosition(){

        Map<Position, List<Employee>> positionToEmployees = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getPosition));

        positionToEmployees.forEach((position, employees) -> {
            System.out.println("Position: " + position);
            employees.forEach(System.out::println);
            System.out.println("\n----------------------------------");
        });
    }


    public static void main(String[] args) {
        employeesAgeGreaterThan30();
        listOfEmployeeNames();
        employeeWithHighestSalary();
        calculateAverageSalary();
        employeesSortedBySurname();
        isAllOlderThan25();
        nameOfEmployeesInIT();
        groupByDepartment();
        countOfEmployeesByDepartment();
        averageAgePerDepartment();
        partitionEmployersAndEmployees();
        getTotalSalary();
        employeeWithLowestSalary();
        listOfUniqueSurnames();
        groupEmployeeNameToSalary();
        notEmployers();
        getFullName();
        updateSalaryBy10Percent();
        salarySumOfEmployeesOfIT();
        sumOfSalariesPerDepartment();
        employeesWithSalaryGreaterThanAverage();
        employeeListToMap();
        namesBySortedSalary();
        findOldestEmployeePerDepartment();
        mergeNameAndSurname();
        flattenListOfList();
        yearsOfExperience();
        findLongestServingEmployee();
        combinedSalaryOfEmployees();
        numberOfEmployeesPerPosition();


    }
}
