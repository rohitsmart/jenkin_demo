package com.hospital.rohit.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
@Setter
@Entity
@Table(name = "UserCredentials")
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonManagedReference
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToOne(targetEntity=Customer.class,cascade = {CascadeType.ALL})
    private Customer customer;

    @OneToOne(targetEntity=Employee.class,cascade = {CascadeType.ALL})
    private Employee employee ;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
