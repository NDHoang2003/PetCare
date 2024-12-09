package com.project.petcare.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petcare.entity.Employee;

@Service
public interface AdminService {
    List<Employee> listOfEmployee();
    Employee saveEmp(Employee emp);
    Employee findEmployee(String id);
    Employee delEmp(String id);
    Employee updateEmp(String id, Employee newEmp);
}