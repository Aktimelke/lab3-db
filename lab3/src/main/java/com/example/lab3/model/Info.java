package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="info")
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="address")
    private String address;
    @Column(name ="typeof")
    private String typeof;
    @Column(name ="model")
    private String model;
    @Column(name ="mader")
    private String mader;
    @Column(name ="addingdate")
    private String addingdate;
}
