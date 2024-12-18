package ru.innotech.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_product")
@Data
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;
    @Column(name = "user_id")
    long userId;
    @Column(name = "acc_num")
    String accNum;
    double balance;
    String type;
}
