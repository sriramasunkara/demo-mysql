package com.example.demo.entities;

import com.example.demo.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleEnum name;
}
