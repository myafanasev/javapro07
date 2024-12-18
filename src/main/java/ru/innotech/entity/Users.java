package ru.innotech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;
    @Column(name = "username")
    String user;

}
