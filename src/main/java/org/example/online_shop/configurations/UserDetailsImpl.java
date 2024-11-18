package org.example.online_shop.configurations;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.online_shop.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Transactional
public class UserDetailsImpl implements UserDetails {

    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(userEntity.getRole().getRoleName()));
        return roles;
    }

    public UserEntity getEmployee(){
        return this.userEntity;
    }

    @Override
    public String getPassword() {
        if(userEntity == null)
            return null;
        if(userEntity.getPassword() == null)
            return  null;
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        if(userEntity == null)
            return null;
        if(userEntity.getUsername() == null)
            return  null;
        return userEntity.getUsername();
    }

    public String getRoleName() {
        return userEntity.getRole().getRoleName();
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
