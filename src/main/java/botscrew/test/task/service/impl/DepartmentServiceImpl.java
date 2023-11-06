package botscrew.test.task.service.impl;

import botscrew.test.task.model.Department;
import botscrew.test.task.repository.DepartmentRepository;
import botscrew.test.task.service.DepartmentService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public String findHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findDepartmentByName(departmentName);
        if (department != null && department.getHead() != null) {
            return "Head of " + departmentName + " department is "
                    + department.getHead().getName() + " " + department.getHead().getLastName();
        } else {
            throw new NoSuchElementException("Department not found or no head assigned");
        }
    }
}
