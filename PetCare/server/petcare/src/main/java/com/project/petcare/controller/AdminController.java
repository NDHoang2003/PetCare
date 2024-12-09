package com.project.petcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.petcare.entity.Employee;
import com.project.petcare.service.admin.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/home")
    public String adminHome() {
        return "This is adminHome";
    }

    @GetMapping("/all_employee")
    public List<Employee> listOfEmployee() {
        return adminService.listOfEmployee();
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
        Employee savedEmp = adminService.saveEmp(emp);
        if (savedEmp != null) return ResponseEntity.ok(savedEmp);
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/employee")
    public ResponseEntity<Employee> employeeDetails(@RequestParam(name = "id") String id) {
        Employee employee = adminService.findEmployee(id);
        if (employee != null) return ResponseEntity.ok(employee);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/employee")
    public ResponseEntity<Employee> deleteEmployee(@RequestParam(name = "id") String id) {
        Employee deletedEmployee = adminService.delEmp(id);
        if (deletedEmployee != null) return ResponseEntity.ok(deletedEmployee);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestParam(name = "id") String id, @RequestBody Employee newEmployee) {
        Employee updatedEmployee = adminService.updateEmp(id, newEmployee);
        if (updatedEmployee != null) return ResponseEntity.ok(updatedEmployee);
        return ResponseEntity.notFound().build();
    }
}