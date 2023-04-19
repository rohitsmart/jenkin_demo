package com.hospital.rohit.user;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {



    private String contact_number;
    private String full_name;

    private String email_id;

    private String father_name;

    private String date_of_birth;



    private String designation;
    private List<AddressDTO> addressDTO;

    private IdentityDTO identityDTO;

    private PancardDTO pancardDTO;

    private AccountDTO accountDTO;




}
