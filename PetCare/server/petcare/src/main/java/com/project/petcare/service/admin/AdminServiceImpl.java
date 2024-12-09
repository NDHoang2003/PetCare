package com.project.petcare.service.admin;

// import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.petcare.entity.Employee;
// import com.project.petcare.entity.User;
import com.project.petcare.repository.EmployeeRepository;
import com.project.petcare.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    EmployeeRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Employee> listOfEmployee() {
       return adminRepository.findAll();
    }

    @Override
    public Employee saveEmp(Employee emp) {
        // Check for duplicate CCCD before saving
        if (adminRepository.findEmpByCccd(emp.getCccd()) != null) {
            return null;
        }
        emp.setIsDel(false); // Default isDel to false (active employee)
        Employee newEmp = adminRepository.save(emp);

        // Temporarily disable user creation
        // if (newEmp != null) {
        //     User newUser = new User(newEmp.getEmail(), "123456789");
        //     newUser = userRepository.save(newUser);
        //     newEmp.setUser(newUser);
        //     newEmp = employeeRepository.save(newEmp);
        // }
        return newEmp;
    }

    @Override
    public Employee findEmployee(String id) {
        return adminRepository.findEmployee(id);
    }

    @Override
    public Employee delEmp(String id) {
        Employee findEmployee = adminRepository.findEmployee(id);
        findEmployee.setIsDel(true);
        adminRepository.save(findEmployee);
        // User findUser = userRepository.findByUsername(findEmployee.getEmail());
        // userRepository.delete(findUser);
        return findEmployee;
    }

    @Override
    public Employee updateEmp(String id, Employee newEmp) {
        Employee existingEmp = adminRepository.findEmployee(id);
        if (existingEmp != null) {
            existingEmp.updateEmployee(newEmp); // Update fields using Employee's method
            return adminRepository.save(existingEmp); // Save and return updated employee
        }
        return null;
        // adminRepository.updateAddress(newEmp.getAddress(), id);
        // adminRepository.updateBDate(newEmp.getBdate(), id);
        // adminRepository.updateCCCD(newEmp.getCccd(), id);
        // adminRepository.updateDate(newEmp.getDate(), id);
        // adminRepository.updateEmail(newEmp.getEmail(), id);
        // adminRepository.updateFName(newEmp.getFirstName(), id);
        // adminRepository.updateLName(newEmp.getLastName(), id);
        // adminRepository.updatePhoneNum(newEmp.getPhoneNum(), id);
        // adminRepository.updatePlace(newEmp.getPlace(), id);
        // adminRepository.updateSex(newEmp.getSex(), id);
    }

    // @Override
    // public Employee updateEmp(Integer id, String pos, String role) {
    //     // TODO Auto-generated method stub
    //     Employee findEmployee = adminRepository.findEmployee(id);
    //     findEmployee.setPos(pos);
    //     findEmployee.setRole(role);
    //     return findEmployee;
    // }
    
  
}
