package com.example.demo.controller;

import com.example.demo.service.EmployeeService;
import com.example.demo.vo.EmployeeVO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("employee")
	@CrossOrigin(origins = "*")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("")
    public ResponseEntity<EmployeeVO>  saveEmployee(@Valid @RequestBody EmployeeVO employeeVO){

       return ResponseEntity.ok( employeeService.saveEmployee(employeeVO));
    }

    @PutMapping("")
    public ResponseEntity<EmployeeVO>  updateEmployee(@Valid @RequestBody EmployeeVO employeeVO){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeVO));
    }


    @PostMapping("/search")
    public ResponseEntity<List<EmployeeVO>>  search(@Valid
                                                         @RequestBody EmployeeVO employeeVO){
        return ResponseEntity.ok(employeeService.seachEmployee(employeeVO));
    }


    @PostMapping("/search/specification")
    public ResponseEntity<List<EmployeeVO>>  searchBySpecification(@RequestBody EmployeeVO employeeVO){
        return ResponseEntity.ok(employeeService.searchEmployeeSpec(employeeVO));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeVO>>  allEmployee(@RequestParam("pageNo") Integer pageNo,
                                                         @RequestParam("size") Integer size  ){
        return ResponseEntity.ok(employeeService.getAllEmployees(pageNo,size));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  allEmployee(@PathVariable("id") Integer id
                                                         ){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


}
