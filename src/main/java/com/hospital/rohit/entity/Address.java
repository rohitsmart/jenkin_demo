package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    private String country;

    private String state;

    private String city;

    private String zip_code;

    @OneToOne(targetEntity=AddressProof.class,cascade = {CascadeType.ALL})
    private AddressProof addressProof;
}
