package com.example.ElectroStore.models;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "Order", schema = "public", catalog = "ElectroStoreDB")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Basket basket;
}