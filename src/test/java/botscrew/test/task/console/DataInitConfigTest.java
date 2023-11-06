package botscrew.test.task.console;

import static botscrew.test.task.console.DataInitConfig.createDepartments;
import static botscrew.test.task.console.DataInitConfig.createLectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import botscrew.test.task.model.Degree;
import botscrew.test.task.model.Department;
import botscrew.test.task.model.Lector;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DataInitConfigTest {

    @Test
    void shouldCreatDepartmentsAndLectors() {
        Set<Department> departments = createDepartments();
        Set<Lector> lectors = createLectors();

        assertEquals(10, lectors.size());
        assertTrue(lectors.stream().anyMatch(lector ->
                lector.getName().equals("John") && lector.getDegree().equals(Degree.PROFESSOR)));
        assertEquals(10, departments.size());
        assertTrue(departments.stream().anyMatch(department -> department.getName().equals("Physics")));
    }
}