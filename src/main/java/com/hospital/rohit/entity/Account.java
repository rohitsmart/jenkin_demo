package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    private String account_type;


    @Column(unique = true)
    private String account_number;

    private String account_opening_cost;

    private String current_balance;

    private String last_login;


    @OneToMany(targetEntity=Statement.class,cascade = {CascadeType.ALL})
    private List  statement;


}
