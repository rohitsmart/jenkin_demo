package com.hospital.rohit.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private String country;

    private String state;

    private String city;

    private String zip_code;

    private AddressProofDTO addressProofDTO;
}
