package com.excelr.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.Repo.EmployeeRepository;
import com.excelr.model.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFullname(employeeDetails.getFullname());
		employee.setEmail(employeeDetails.getEmail());
		employee.setGender(employeeDetails.getGender());
		employee.setDob(employeeDetails.getDob());
		employee.setMobilenumber(employeeDetails.getMobilenumber());
		employee.setEmergencycontactnumber(employeeDetails.getEmergencycontactnumber());
		employee.setCompanyname(employeeDetails.getCompanyname());
		employee.setEmpcode(employeeDetails.getEmpcode());
		employee.setOfficelocation(employeeDetails.getOfficelocation());
		employee.setReportingmanager(employeeDetails.getReportingmanager());
		employee.setHrname(employeeDetails.getHrname());
		employee.setJoiningdate(employeeDetails.getJoiningdate());
		employee.setHrname(employeeDetails.getHrname());
		employee.setOffid(employeeDetails.getOffid());
		employee.setAadharnumber(employeeDetails.getAadharnumber());
		employee.setPancardnumber(employeeDetails.getPancardnumber());
		employee.setBankaccountnumber(employeeDetails.getBankaccountnumber());
		employee.setIfsc(employeeDetails.getIfsc());

        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesByProjectId(Long projectId) {
        return employeeRepository.findByProjectId(projectId);
    }
}

