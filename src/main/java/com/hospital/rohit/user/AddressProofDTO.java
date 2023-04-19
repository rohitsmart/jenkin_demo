package com.hospital.rohit.user;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressProofDTO {


    private String address_type;


    private String address_number;
}
