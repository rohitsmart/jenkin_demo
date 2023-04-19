package com.hospital.rohit.user;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDTO {

    private String identity_type;

    private String identity_number;
}
