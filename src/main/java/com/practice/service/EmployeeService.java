package com.practice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice.dto.EmployeeDto;
import com.practice.entity.Employee;
import com.practice.exception.ResourceNotFound;
import com.practice.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	private EmployeeRepository employeeRepository;
	private ModelMapper modelMapper;

	public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper) 
	{
		this.employeeRepository = employeeRepository;
		this.modelMapper=modelMapper;
	}
	
	EmployeeDto mapToDto(Employee employee)
	{
		EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
		return dto;
	}
	
	Employee mapToEntity(EmployeeDto dto)
	{
		Employee employee = modelMapper.map(dto, Employee.class);
		return employee;
	}

	public EmployeeDto addEmployee(EmployeeDto dto) 
	{
		Employee employee = mapToEntity(dto);
		Employee emp = employeeRepository.save(employee);
		EmployeeDto empDto = mapToDto(emp);
		return empDto;
	}

	public List<EmployeeDto> getAllEmployee(int pageSize, int pageNo, String sortBy, String sortDir) 
	{
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		PageRequest page = PageRequest.of(pageNo, pageSize, sort);
		Page<Employee> all = employeeRepository.findAll(page);
		List<Employee> employee = all.getContent();
		List<EmployeeDto> empDto = employee.stream().map(e->mapToDto(e)).collect(Collectors.toList());
		return empDto;
	}

	public EmployeeDto getEmployeeById(long empId) 
	{
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFound("RECORD NOT FOUND WITH ID : "+empId));
		return mapToDto(employee);
	}

	public EmployeeDto updateEmployee(long id, EmployeeDto dto) 
	{
		Employee employee = mapToEntity(dto);
		employee.setId(id);
		Employee emp = employeeRepository.save(employee);
		return mapToDto(emp);
	}

	public void deleteById(long id) 
	{
		employeeRepository.deleteById(id);
	}
	
	
	
}
