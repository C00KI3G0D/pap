package org.springframework.security.core.userdetails;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class User implements UserDetails {

    public User(String email, String pass, Set<GrantedAuthority> authorities) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

}
