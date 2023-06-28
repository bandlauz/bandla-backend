package uz.nazarovctrl.bandla.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.nazarovctrl.bandla.enums.Role;
import uz.nazarovctrl.bandla.enums.Status;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private String email;
    private String password;
    private Status status;
    private Role role;
    private boolean isVisible;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<>();
        list.add(new SimpleGrantedAuthority(role.name()));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(Status.BLOCK);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isVisible && (!status.equals(Status.NOT_VERIFIED));
    }
}
