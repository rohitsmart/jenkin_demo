package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "AddressProof")
@NoArgsConstructor
@AllArgsConstructor
public class AddressProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    private String address_type;

    @Column(unique = true)
    private String address_number;
}
