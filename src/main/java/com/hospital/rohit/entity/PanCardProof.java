package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "PanCardProof")
@NoArgsConstructor
@AllArgsConstructor
public class PanCardProof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    @Column(unique = true)
    private String pancard_number;

}
