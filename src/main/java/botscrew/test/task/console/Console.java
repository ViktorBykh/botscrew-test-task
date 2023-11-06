package botscrew.test.task.console;

import botscrew.test.task.model.Lector;
import botscrew.test.task.service.DepartmentService;
import botscrew.test.task.service.LectorService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class Console {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    @ShellMethod("Start the console interface")
    public void start() {
        System.out.println("Welcome to the University Console Interface! "
                + "Enter a command (type 'exit' to quit): ");
    }

    @ShellMethod(value = "Find the head of a department", key = "Who is head of department")
    public void whoIsHeadOfDepartment(String departmentName) {
        System.out.println(departmentService.findHeadOfDepartment(departmentName));
    }

    @ShellMethod(value = "Department statistics", key = "Show statistics for")
    public void showDepartmentStatistics(String departmentName) {
        Map<String, Long> statistics = lectorService.getDepartmentStatistics(departmentName);
        if (!statistics.isEmpty()) {
            System.out.println(departmentName + " department statistics: ");
            statistics.forEach((degree, count) -> System.out.println(degree + " - " + count));
        } else {
            System.out.println("No statistics available for " + departmentName);
        }
    }

    @ShellMethod(value = "The average salary for the department",
            key = "Show the average salary for the department")
    public void showAverageSalary(String departmentName) {
        double averageSalary = lectorService.getAverageSalaryForDepartment(departmentName);
        System.out.println("The average salary of " + departmentName + " is " + averageSalary);
    }

    @ShellMethod(value = "Employee count for a department", key = "Show count of employee for")
    public void showEmployeeCount(String departmentName) {
        int employeeCount = lectorService.getCountOfEmployeesForDepartment(departmentName);
        System.out.println("Employee count for " + departmentName
                + " department: " + employeeCount);
    }

    @ShellMethod(value = "Global search by template", key = "Global search by")
    public void findByTemplate(String template) {
        List<Lector> searchResult = lectorService.findByTemplate(template);
        if (!searchResult.isEmpty()) {
            System.out.println("Search results for \"" + template + "\":");
            searchResult.forEach(lector ->
                    System.out.println(lector.getName() + " " + lector.getLastName()));
        } else {
            System.out.println("No results found for \"" + template + "\".");
        }
    }
}
