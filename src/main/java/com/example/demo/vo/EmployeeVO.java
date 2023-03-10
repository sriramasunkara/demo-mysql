package com.example.demo.vo;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeVO implements Serializable {

    private  Integer id;

    private String name;

    private String company;

    private Double salary;
}
