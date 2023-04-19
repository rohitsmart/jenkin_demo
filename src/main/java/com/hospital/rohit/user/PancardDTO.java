package com.hospital.rohit.user;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PancardDTO {

    private String pancard_number;
}
