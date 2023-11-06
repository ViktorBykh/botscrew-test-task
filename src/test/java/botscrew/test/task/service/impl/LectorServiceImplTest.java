package botscrew.test.task.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import botscrew.test.task.model.Degree;
import botscrew.test.task.model.Department;
import botscrew.test.task.model.Lector;
import botscrew.test.task.repository.DepartmentRepository;
import botscrew.test.task.repository.LectorRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LectorServiceImplTest {
    private String departmentName;
    private Department department;
    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorServiceImpl lectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentName = "Computer Science";
        department = new Department();
    }

    @Test
    void shouldReturnStatistics() {
        Lector assistant = new Lector().setDegree(Degree.ASSISTANT);
        Lector associateProfessor = new Lector().setDegree(Degree.ASSOCIATE_PROFESSOR);
        Lector professor = new Lector().setDegree(Degree.PROFESSOR);
        department.setLectors(Set.of(assistant, associateProfessor, professor));

        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        Map<String, Long> statistics = lectorService.getDepartmentStatistics(departmentName);
        assertEquals(1, statistics.get("assistants"));
        assertEquals(1, statistics.get("associate professors"));
        assertEquals(1, statistics.get("professors"));
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenDepartmentNotFound() {
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(null);

        assertThrows(NoSuchElementException.class, () ->
                lectorService.getDepartmentStatistics(departmentName));
    }

    @Test
    void shouldReturnAverageSalary() {
        Lector lector1 = new Lector().setSalary(BigDecimal.valueOf(2000));
        Lector lector2 = new Lector().setSalary(BigDecimal.valueOf(3000));
        department.setLectors(Set.of(lector1, lector2));

        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        double averageSalary = lectorService.getAverageSalaryForDepartment(departmentName);
        assertEquals(2500.0, averageSalary);
    }

    @Test
    void shouldReturnZeroAverageSalaryWhenNoLectors() {
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        double averageSalary = lectorService.getAverageSalaryForDepartment(departmentName);
        assertEquals(0.0, averageSalary);
    }

    @Test
    void shouldReturnEmployeeCount() {
        Lector lector1 = new Lector().setName("Mike").setLastName("Douglas");
        Lector lector2 = new Lector().setName("Alasdair").setLastName("MacIntyre");
        department.setLectors(Set.of(lector1, lector2));

        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        int employeeCount = lectorService.getCountOfEmployeesForDepartment(departmentName);
        assertEquals(2, employeeCount);
    }

    @Test
    void shouldReturnZeroCountOfEmployeesWhenNoLectors() {
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        int employeeCount = lectorService.getCountOfEmployeesForDepartment(departmentName);
        assertEquals(0, employeeCount);
    }

    @Test
    void shouldReturnSearchResults() {
        String template = "John";
        List<Lector> searchResults = List.of(new Lector().setName("John Doe"),
                new Lector().setName("Johnny Bravo"));

        when(lectorRepository
                .findLectorsByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(template, template))
                .thenReturn(searchResults);

        List<Lector> result = lectorService.findByTemplate(template);
        assertIterableEquals(searchResults, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoResults() {
        String template = "Jams";

        when(lectorRepository
                .findLectorsByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(template, template))
                .thenReturn(List.of());

        List<Lector> result = lectorService.findByTemplate(template);
        assertTrue(result.isEmpty());
    }
}