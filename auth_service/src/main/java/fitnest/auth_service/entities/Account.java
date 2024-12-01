package fitnest.auth_service.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="_account")
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Ensure this is unique to the Account
    private String password;
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the authorities associated with the account
        return null; // Implement this method properly
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement as per your logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement as per your logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement as per your logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement as per your logic
    }
}