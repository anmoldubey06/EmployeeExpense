package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.cg.entity.Employee;
import com.cg.exception.EmployeeException;
import com.cg.exception.LoginDetailsException;
import com.cg.repository.EmployeeRepository;
import com.cg.repository.LoginDetailsRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository eRepo;

	@Autowired
	private LoginDetailsRepository ldRepo;

	public String addEmployee(Employee employee) {
		String email = employee.getEmpEmailId();
		int checkLoginDetailsId = employee.getLogindetails().getId();
		try {
			if (eRepo.existsByEmpEmailId(email)) {
				throw new EmployeeException("Employee with this email already exists");
			} else if (!ldRepo.existsById(checkLoginDetailsId)) {
				throw new LoginDetailsException("Login Detail does not exist");
			} else {
				eRepo.save(employee);
			}
		} catch (EmployeeException ex) {
			throw ex;
		}
		return "Employee Added";
	}

	public List<Employee> getEmployees() {
		try {
			if (eRepo.count() == 0) {
				throw new EmployeeException("Employee Table is empty");
			}
		} catch (EmployeeException ex) {
			throw ex;
		}
		return eRepo.findAll();
	}

	public ResponseEntity<Object> getEmployeeById(int id) {
		try {
			if (!eRepo.existsById(id)) {
				throw new EmployeeException("Employee Not Found");
			}
		} catch (EmployeeException ex) {
			throw ex;
		}
		return new ResponseEntity<Object>(eRepo.findById(id).get(), HttpStatus.OK);
	}

	public String deleteEmployee(int id) {

		try {
			if (!eRepo.existsById(id)) {
				throw new EmployeeException("No such employee exists");
			}
		} catch (EmployeeException ex) {
			throw ex;
		}
		eRepo.deleteById(id);
		return "Record Deleted Successfully";
	}

	public Employee updateEmployee(Employee employee) {
		return eRepo.save(employee);
	}

	public Employee getEmployeeByAll(String userName, String password, String role) {
		List<Employee> el = eRepo.findAll();
		for (Employee e : el) {
			if (e.getUserName().equals(userName) && e.getPassword().equals(password) && e.getRole().equals(role)) {
				return e;
			}
		}
		return null;
	}

	// Methods for testing
	public Employee addEmployeefortest(Employee employee) {
		return eRepo.save(employee);
	}

	public Employee GetEmployeeByEmpname(Employee employee) {
		return eRepo.findByEmpName(employee.getEmpName());
	}

	public void deleteEmployee(Employee employee) {
		eRepo.delete(employee);
	}

}
