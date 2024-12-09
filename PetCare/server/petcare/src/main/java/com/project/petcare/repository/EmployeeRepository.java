package com.project.petcare.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

import com.project.petcare.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Override
    @Query("SELECT e FROM Employee e WHERE e.isDel = false")
    public List<Employee> findAll(); // Fetch only active employees

    @Query("SELECT e FROM Employee e WHERE e.id = ?1 AND e.isDel = false")
    public Employee findEmployee(String id); // Fetch employee by ID, ensuring it's not marked as deleted

    @Query("SELECT e.isDel FROM Employee e WHERE e.email = ?1")
    public Boolean checkIsDel(String email); // Check if an employee is logically deleted by email

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.cccd = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateCCCD(String cccd, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.placeOfIssue = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updatePlace(String placeOfIssue, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.issueDate = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateDate(LocalDate issueDate, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.birthDate = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateBDate(LocalDate birthDate, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.gender = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateSex(String gender, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.phoneNumber = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updatePhoneNum(String phoneNumber, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.firstName = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateFName(String firstName, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.middleAndLastName = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateLName(String middleAndLastName, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.salary = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateSalary(BigDecimal salary, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.managerId = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateManager(String managerId, String id);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.email = ?1 WHERE e.id = ?2 AND e.isDel = false")
    public void updateEmail(String email, String id);

    @Query("SELECT e FROM Employee e WHERE e.cccd = ?1 AND e.isDel = false")
    public Employee findEmpByCccd(String cccd); // Find employee by CCCD, ensuring it's not deleted

    @Query("SELECT CONCAT(e.middleAndLastName, ' ', e.firstName) AS employeeName " +
           "FROM Employee e " +
           "WHERE e.id = ?1 AND e.isDel = false")
    public String findEmployeeWithHighestOrders(Integer employeeId); // Fetch employee's name if active
}
