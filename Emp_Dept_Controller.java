package com.example.practice2;


import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController

public class Emp_Dept_Controller {

    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;

    public Emp_Dept_Controller(EmployeeRepository empRepo, DepartmentRepository deptRepo){
        this.empRepo = empRepo;
        this.deptRepo = deptRepo;
    }

    @PostMapping("/emp")
    public Employee addEmp(@RequestBody Employee employee){
        return empRepo.save(employee);
    }

    @PostMapping("/dept")
    public Department addDept(@RequestBody Department department){
        return deptRepo.save(department);
    }

    @GetMapping("/getemp/{id}")
    public Employee getEmployee(@PathVariable long id){
        return empRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found!"));


    }

    // enroll employee in a department
    @PostMapping("/enroll/{empId}/{deptId}")
    public Map<String, String> enrollEmpl(@PathVariable long empId, @PathVariable long deptId){
        Map<String, String> response = new HashMap<>();
        Employee employee = empRepo.findById(empId).orElseThrow(() -> new RuntimeException("Employee with ID " + empId + " not found!"));

        Department department = deptRepo.findById(deptId).orElseThrow(() -> new RuntimeException("Department with ID " + deptId + " not found!"));

        employee.enrollInDepartment(department);
        empRepo.save(employee);

        response.put("message", "Employee enrolled successfully");
        return response;
    }

    // get departments of an employees
    @GetMapping("/emp/{id}/dept")
    public Set<Department> getDept(@PathVariable long id){
        Employee employee = empRepo.findById(id).orElse(null);
        return employee != null ? employee.getDepartments() : null;
    }

    // update employee
    @PutMapping("/emp/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmp){
        Employee employee = empRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee with id " + id + " not found!"));

        employee.setName(updatedEmp.getName());
        employee.setSalary(updatedEmp.getSalary());
        return empRepo.save(employee);
    }

    // delete employee
    @DeleteMapping("/emp/{id}")
    public Map<String , String> deleteEmp(@PathVariable long id){
        Employee employee = empRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee with id " + id + " not found!"));
        empRepo.delete(employee);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully!");
        return response;
    }


}
