package botscrew.test.task.service.impl;

import botscrew.test.task.model.Degree;
import botscrew.test.task.model.Department;
import botscrew.test.task.model.Lector;
import botscrew.test.task.repository.DepartmentRepository;
import botscrew.test.task.repository.LectorRepository;
import botscrew.test.task.service.LectorService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LectorServiceImpl implements LectorService {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Override
    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        Department department = departmentRepository.findDepartmentByName(departmentName);
        Map<String, Long> statistics = new HashMap<>();

        if (department != null) {
            long assistantsCount = department.getLectors().stream()
                    .filter(lector -> lector.getDegree() == Degree.ASSISTANT)
                    .count();
            long associateProfessorsCount = department.getLectors().stream()
                    .filter(lector -> lector.getDegree() == Degree.ASSOCIATE_PROFESSOR)
                    .count();
            long professorsCount = department.getLectors().stream()
                    .filter(lector -> lector.getDegree() == Degree.PROFESSOR)
                    .count();

            statistics.put("assistants", assistantsCount);
            statistics.put("associate professors", associateProfessorsCount);
            statistics.put("professors", professorsCount);

            return statistics;
        } else {
            throw new NoSuchElementException("Not found department: " + departmentName);
        }
    }

    @Override
    public double getAverageSalaryForDepartment(String departmentName) {
        Department department = departmentRepository.findDepartmentByName(departmentName);
        if (department != null && !department.getLectors().isEmpty()) {
            double totalSalary = department.getLectors().stream()
                    .map(Lector::getSalary)
                    .mapToDouble(BigDecimal::doubleValue)
                    .sum();
            return Math.floor((totalSalary / department.getLectors().size()) * 100) / 100;
        }
        return 0.0;
    }

    @Override
    public int getCountOfEmployeesForDepartment(String departmentName) {
        Department department = departmentRepository.findDepartmentByName(departmentName);
        return department != null ? department.getLectors().size() : 0;
    }

    @Override
    public List<Lector> findByTemplate(String template) {
        return lectorRepository
                .findLectorsByNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
                        template, template);
    }
}
