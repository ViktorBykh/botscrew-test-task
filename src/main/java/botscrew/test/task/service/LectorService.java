package botscrew.test.task.service;

import botscrew.test.task.model.Lector;
import java.util.List;
import java.util.Map;

public interface LectorService {
    Map<String, Long> getDepartmentStatistics(String departmentName);

    double getAverageSalaryForDepartment(String departmentName);

    int getCountOfEmployeesForDepartment(String departmentName);

    List<Lector> findByTemplate(String template);
}
