package com.example.demo.service.impl;

import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.vo.EmployeeVO;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeVO saveEmployee(EmployeeVO employeeVo){
       Employee emp = Employee.builder().id(
                employeeVo.getId()
        ).name(employeeVo.getName()).company(employeeVo.getCompany()).salary(employeeVo.getSalary()).build();

      Employee em= employeeRepository.save(emp);

      return EmployeeVO.builder().id(em.getId()).name(em.getName()).company(em.getCompany()).salary(em.getSalary()).build();
    }

    public EmployeeVO updateEmployee(EmployeeVO employeeVo){
        if(Objects.isNull(employeeVo.getId())){
            throw new IllegalArgumentException("Employee Id is required while updating employee");
        }
        Employee emp = Employee.builder().id(
                employeeVo.getId()
        ).name(employeeVo.getName()).company(employeeVo.getCompany()).salary(employeeVo.getSalary()).build();

        Employee em= employeeRepository.save(emp);

        return EmployeeVO.builder().id(em.getId()).name(em.getName()).company(em.getCompany()).salary(em.getSalary()).build();
    }



    public Page<EmployeeVO> getAllEmployees(Integer pageNo,Integer size){
       Pageable pageable= PageRequest.of(pageNo,size);
       Page<Employee> emp= employeeRepository.findAll(pageable);
       List<EmployeeVO> empvo =emp.stream().map(e-> EmployeeVO.builder().id(e.getId()).name(e.getName()).company(e.getCompany()).salary(e.getSalary()).build()).collect(Collectors.toList());
       return new PageImpl(empvo);

    }

    public List<EmployeeVO> seachEmployee(EmployeeVO em){
        Example<Employee> example=Example.of(Employee.builder().id(
                em.getId()
        ).name(em.getName()).company(em.getCompany()).salary(em.getSalary()).build());
          List<Employee> emp=  employeeRepository.findAll(example);
        return emp.stream().map(e-> EmployeeVO.builder().id(e.getId()).name(e.getName()).company(e.getCompany()).salary(e.getSalary()).build()).collect(Collectors.toList());
    }

    public List<EmployeeVO> searchEmployeeSpec(EmployeeVO em){
        List<Employee> emp =  employeeRepository.findAll((root,query,builder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(em.getId())){
                predicates.add(builder.equal(root.get("id"),em.getId()));
            }
            if(Objects.nonNull(em.getName())){
                predicates.add(builder.equal(root.get("name"),em.getName()));
            }
            if(Objects.nonNull(em.getCompany())){
                predicates.add(builder.equal(root.get("company"),em.getCompany()));
            }
            if(Objects.nonNull(em.getSalary())){
                predicates.add(builder.equal(root.get("salary"),em.getSalary()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        });

        return emp.stream().map(e-> EmployeeVO.builder().id(e.getId()).name(e.getName()).company(e.getCompany()).salary(e.getSalary()).build()).collect(Collectors.toList());

    }


    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }

}
