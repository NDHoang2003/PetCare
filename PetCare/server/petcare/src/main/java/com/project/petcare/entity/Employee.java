package com.project.petcare.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Entity
@Table(name = "nhan_vien") 
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "NVid", length = 10)
    private String id; 

    @Column(name = "CCCD", length = 12, unique = true)
    private String cccd; 

    @Column(name = "NoiCap", length = 3)
    private String placeOfIssue; 

    @Column(name = "NgayCap")
    @Temporal(TemporalType.DATE)
    private LocalDate issueDate;

    @Column(name = "GioiTinh", length = 6)
    private String gender;

    @Column(name = "SDT", length = 10)
    private String phoneNumber; 

    @Column(name = "HoVaTenDem", length = 30)
    private String middleAndLastName; 

    @Column(name = "Ten", length = 20)
    private String firstName; 
    @Column(name = "MucLuong", precision = 10, scale = 2)
    private BigDecimal salary; 

    @Column(name = "QuanLiid", length = 10)
    private String managerId;

    @Column(name = "NgaySinh")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "isDel", nullable = false)
    private Boolean isDel = false;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Order> orders; 

    public Employee(String id, String cccd, String placeOfIssue, LocalDate issueDate, String gender, String phoneNumber,
                    String middleAndLastName, String firstName, BigDecimal salary, String managerId, LocalDate birthDate) {
        this.id = id;
        this.cccd = cccd;
        this.placeOfIssue = placeOfIssue;
        this.issueDate = issueDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.middleAndLastName = middleAndLastName;
        this.firstName = firstName;
        this.salary = salary;
        this.managerId = managerId;
        this.birthDate = birthDate;
        this.isDel = false;
    }

    public void updateEmployee(Employee newEmployee) {
        this.cccd = newEmployee.getCccd();
        this.placeOfIssue = newEmployee.getPlaceOfIssue();
        this.issueDate = newEmployee.getIssueDate();
        this.gender = newEmployee.getGender();
        this.phoneNumber = newEmployee.getPhoneNumber();
        this.middleAndLastName = newEmployee.getMiddleAndLastName();
        this.firstName = newEmployee.getFirstName();
        this.salary = newEmployee.getSalary();
        this.managerId = newEmployee.getManagerId();
        this.birthDate = newEmployee.getBirthDate();
        this.isDel = newEmployee.getIsDel();
    }
}