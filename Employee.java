package com.example.practice2;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int code;
    private double salary;

    @ManyToMany
    @JoinTable(
            name = "emp_dept",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    public Employee(){}

    public Employee(String name, int code, double salary){
        this.name = name;
        this.code = code;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void enrollInDepartment(Department department){
        if(departments == null){
            departments = new HashSet<>();
        }
        this.departments.add(department);
        department.getEmployees().add(this);
    }
}
