package com.example.practice2;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String job_role;

    @ManyToMany(mappedBy = "departments")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

    public Department(){}

    public Department(String job_role){
        this.job_role = job_role;
    }

    public long getId() {
        return id;
    }

    public String getJob_role() {
        return job_role;
    }

    public void setJob_role(String job_role) {
        this.job_role = job_role;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
}
