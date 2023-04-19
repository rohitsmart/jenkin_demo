package com.hospital.rohit.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String message;

    private String bearer_token;

    private String refresh_token;

    private String response_user_id;

    private String response_full_name;

    private String response_user_contact_number;

    private String response_account_type;

    private String response_account_current_balance;

    private String response_last_login_date;
}
