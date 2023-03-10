package com.example.demo.service;

import com.example.demo.vo.EmployeeVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeVO saveEmployee(EmployeeVO employeeVo);

    EmployeeVO updateEmployee(EmployeeVO employeeVo);

     Page<EmployeeVO> getAllEmployees(Integer pageNo, Integer size);

    List<EmployeeVO> seachEmployee(EmployeeVO em);

    List<EmployeeVO> searchEmployeeSpec(EmployeeVO em);

    void deleteEmployee(Integer id);


}
