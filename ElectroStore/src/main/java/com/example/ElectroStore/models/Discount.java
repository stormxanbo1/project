package com.example.ElectroStore.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="Discount", schema = "public", catalog = "ElectroStoreDB")
public class    Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "percent")
    private int percent;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}