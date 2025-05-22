package in.bushansirgur.springbootcrud.springbootcrud.service;

import in.bushansirgur.springbootcrud.springbootcrud.model.Employee;
import in.bushansirgur.springbootcrud.springbootcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(Long id){
        return employeeRepository.findById(id);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Long id, Employee newData) {
        return employeeRepository.findById(id)
                .map(existing -> {
                    existing.setName(newData.getName());
                    existing.setGender(newData.getGender());
                    existing.setDepartment(newData.getDepartment());
                    return employeeRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + id));
    }

}
