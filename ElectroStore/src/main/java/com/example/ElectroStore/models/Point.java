package com.example.ElectroStore.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Point", schema = "public", catalog = "ElectroStoreDB")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Address")
    private String address;
    @Column(name = "employeeQuantity")
    private int employeeQuantity;
    @Column(name = "Phone_Number")
    private String phoneNumber;
}