package com.example.springpostgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name="arrays")
@AllArgsConstructor
@NoArgsConstructor
public class Array {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @ElementCollection
    private List<Integer> data;


}
