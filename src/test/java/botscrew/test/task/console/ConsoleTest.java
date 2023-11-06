package botscrew.test.task.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import botscrew.test.task.model.Degree;
import botscrew.test.task.model.Lector;
import botscrew.test.task.service.DepartmentService;
import botscrew.test.task.service.LectorService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ConsoleTest {
    private String departmentName;
    @Mock
    private DepartmentService departmentService;

    @Mock
    private LectorService lectorService;

    @InjectMocks
    private Console console;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentName = "Computer Science";
    }

    @Test
    void shouldPrintHeadOfDepartment() {
        String expectedHeadName = "John Doe";

        when(departmentService.findHeadOfDepartment(departmentName)).thenReturn(expectedHeadName);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        console.whoIsHeadOfDepartment(departmentName);
        System.setOut(System.out);
        String expected = "Head of " + departmentName + " department is " + expectedHeadName;
        String actual = "Head of " + departmentName + " department is " + outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldPrintSearchResults() {
        String template = "John";
        List<Lector> searchResult = List.of(
                new Lector().setName("John").setLastName("Doe").setDegree(Degree.PROFESSOR),
                new Lector().setName("Johnny").setLastName("Smith").setDegree(Degree.ASSISTANT));

        when(lectorService.findByTemplate(template)).thenReturn(searchResult);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        console.findByTemplate(template);
        System.setOut(System.out);
        String expected = "Search results for \"" + template + "\":" +
                System.lineSeparator() + "John Doe" +
                System.lineSeparator() + "Johnny Smith";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldPrintAverageSalary() {
        double averageSalary = 2500.0;

        when(lectorService.getAverageSalaryForDepartment(departmentName)).thenReturn(averageSalary);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        console.showAverageSalary(departmentName);
        System.setOut(System.out);
        String expected = "The average salary of " + departmentName + " is " + averageSalary;
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldPrintEmployeeCount() {
        int employeeCount = 5;
        when(lectorService.getCountOfEmployeesForDepartment(departmentName)).thenReturn(employeeCount);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        console.showEmployeeCount(departmentName);
        System.setOut(System.out);
        String expected = "Employee count for " + departmentName + " department: " + employeeCount;
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldPrintSearchStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("assistants", 2L);
        statistics.put("associate professors", 3L);
        when(lectorService.getDepartmentStatistics(departmentName)).thenReturn(statistics);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        console.showDepartmentStatistics(departmentName);
        System.setOut(System.out);

        String expected = departmentName + " department statistics: " +
                System.lineSeparator() + "assistants - 2" +
                System.lineSeparator() + "associate professors - 3";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}