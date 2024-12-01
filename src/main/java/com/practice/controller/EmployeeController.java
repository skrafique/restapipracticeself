package com.practice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.dto.EmployeeDto;
import com.practice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController 
{
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) 
	{
		this.employeeService = employeeService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createEmployee(@Valid@RequestBody EmployeeDto dto,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		EmployeeDto empDto = employeeService.addEmployee(dto);
		return new ResponseEntity<EmployeeDto>(empDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmp(
		@RequestParam(name="pageSize" , required=false , defaultValue = "5") int pageSize,
		@RequestParam(name="pageNo" , required=false , defaultValue = "0") int pageNo,
		@RequestParam(name="sortBy" , required=false , defaultValue = "id") String sortBy,
		@RequestParam(name="sortDir" , required=false , defaultValue = "asc") String sortDir
		
	)
	{
		List<EmployeeDto> empDto = employeeService.getAllEmployee(pageSize,pageNo,sortBy,sortDir);
		return new ResponseEntity<List<EmployeeDto>>(empDto,HttpStatus.OK);
	}
	
	@GetMapping("/employeeId/{empId}")
	public ResponseEntity<EmployeeDto> getEmpById(@PathVariable long empId)
	{
		EmployeeDto empDto = employeeService.getEmployeeById(empId);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<EmployeeDto> updateEmp(@RequestParam long id,@RequestBody EmployeeDto dto)
	{
		EmployeeDto empDto = employeeService.updateEmployee(id,dto);
		return new ResponseEntity<EmployeeDto>(empDto,HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> delEmp(@RequestParam long id)
	{
		employeeService.deleteById(id);
		return new ResponseEntity<String>("DATA IS DELETED",HttpStatus.OK);
	}
}
