package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Statement")
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;


    @Column(unique = true)
    @ColumnDefault("0")
    private String transaction_id;

    @ColumnDefault("0")
    private double debit;

    @ColumnDefault("0")
    private double credit;

    @ColumnDefault("0")
    private double initial_amount;

    @ColumnDefault("0")
    private double final_amount;

    @ColumnDefault("0")
    private String transaction_date;

    @ColumnDefault("0")
    private String transaction_status;//failed or success



}
