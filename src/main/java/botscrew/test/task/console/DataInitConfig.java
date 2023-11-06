package botscrew.test.task.console;

import botscrew.test.task.model.Degree;
import botscrew.test.task.model.Department;
import botscrew.test.task.model.Lector;
import botscrew.test.task.repository.DepartmentRepository;
import botscrew.test.task.repository.LectorRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DataInitConfig {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @PostConstruct
    public void run() {
        Set<Department> departments = createDepartments();
        Set<Lector> lectors = createLectors();

        departmentRepository.saveAll(departments);
        lectorRepository.saveAll(lectors);

        departments.forEach(department -> {
            Department existingDepartment =
                    departmentRepository.findDepartmentByName(department.getName());
            existingDepartment.setLectors(lectors);
            existingDepartment.setHead(lectors.iterator().next());
            departmentRepository.save(existingDepartment);
        });
    }

    public static Set<Department> createDepartments() {
        return Set.of(
                new Department().setName("Physics"),
                new Department().setName("Computer Science"),
                new Department().setName("Mathematics"),
                new Department().setName("Biology"),
                new Department().setName("Chemistry"),
                new Department().setName("History"),
                new Department().setName("Literature"),
                new Department().setName("Economics"),
                new Department().setName("Psychology"),
                new Department().setName("Engineering"));
    }

    public static Set<Lector> createLectors() {
        return Set.of(
                new Lector()
                        .setName("John")
                        .setLastName("Doe")
                        .setDegree(Degree.PROFESSOR)
                        .setSalary(BigDecimal.valueOf(3000)),
                new Lector()
                        .setName("Jane")
                        .setLastName("Smith")
                        .setDegree(Degree.ASSOCIATE_PROFESSOR)
                        .setSalary(BigDecimal.valueOf(2000)),
                new Lector()
                        .setName("Bob")
                        .setLastName("Johnson")
                        .setDegree(Degree.ASSISTANT)
                        .setSalary(BigDecimal.valueOf(1000)),
                new Lector()
                        .setName("Alice")
                        .setLastName("Brown")
                        .setDegree(Degree.PROFESSOR)
                        .setSalary(BigDecimal.valueOf(3200)),
                new Lector()
                        .setName("Ivan")
                        .setLastName("Petrenko")
                        .setDegree(Degree.ASSOCIATE_PROFESSOR)
                        .setSalary(BigDecimal.valueOf(2100)),
                new Lector()
                        .setName("Petro")
                        .setLastName("Ivanov")
                        .setDegree(Degree.ASSISTANT)
                        .setSalary(BigDecimal.valueOf(1100)),
                new Lector()
                        .setName("Eva")
                        .setLastName("Green")
                        .setDegree(Degree.PROFESSOR)
                        .setSalary(BigDecimal.valueOf(3400)),
                new Lector()
                        .setName("Frank")
                        .setLastName("Miller")
                        .setDegree(Degree.ASSOCIATE_PROFESSOR)
                        .setSalary(BigDecimal.valueOf(2200)),
                new Lector()
                        .setName("Grace")
                        .setLastName("Davis")
                        .setDegree(Degree.ASSISTANT)
                        .setSalary(BigDecimal.valueOf(1200)),
                new Lector()
                        .setName("Henry")
                        .setLastName("Hohnson")
                        .setDegree(Degree.PROFESSOR)
                        .setSalary(BigDecimal.valueOf(3600)));
    }
}
