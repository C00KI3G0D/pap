package crm.local.pap.services;

    import java.util.Set;
    import java.util.stream.Collectors;

    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import crm.local.pap.models.User;
    import crm.local.pap.repositories.UserRepository;

    @Service
    public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;
        public CustomUserDetailsService(UserRepository userRepository) {    
            this.userRepository = userRepository;
            }
            @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(email + " not found." ));

            Set<GrantedAuthority> authorities = user
                    .getRoles()
                    .stream()
                    .map((role) -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        }
    }
