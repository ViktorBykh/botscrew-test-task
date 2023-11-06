package botscrew.test.task.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import botscrew.test.task.model.Department;
import botscrew.test.task.model.Lector;
import botscrew.test.task.repository.DepartmentRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DepartmentServiceImplTest {
    private String departmentName;
    private Department department;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentName = "Computer Science";
        department = new Department();
    }

    @Test
    void shouldReturnHeadOfDepartment() {
        Lector head = new Lector().setName("John").setLastName("Doe");
        department.setHead(head);

        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        String result = departmentService.findHeadOfDepartment(departmentName);
        assertEquals("Head of " + departmentName + " department is John Doe", result);
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenDepartmentNotFound() {
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(null);

        assertThrows(NoSuchElementException.class, () ->
                departmentService.findHeadOfDepartment(departmentName));
    }

    @Test
    void shouldThrowNoSuchElementExceptionWhenNoHeadAssigned() {
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(department);

        assertThrows(NoSuchElementException.class, () ->
                departmentService.findHeadOfDepartment(departmentName));
    }
}