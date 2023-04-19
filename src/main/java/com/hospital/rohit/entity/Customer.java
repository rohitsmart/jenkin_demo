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
@Table(name = "Customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    private String contact_number;
    private String full_name;



    private String email_id;
    private String father_name;

    private String date;

    private String profession;

    @ColumnDefault("0")
    private String date_of_birth;

    @OneToMany(targetEntity=Address.class,cascade = {CascadeType.ALL})
    private List address;



    @OneToOne(targetEntity=Identity.class,cascade = {CascadeType.ALL})
    private Identity identity;

    @OneToOne(targetEntity=PanCardProof.class,cascade = {CascadeType.ALL})
    private PanCardProof panCardProof;


    @OneToOne(targetEntity=Account.class,cascade = {CascadeType.ALL})
    private Account account;



}
