package pe.isil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.isil.model.User;
import pe.isil.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RetiUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User currentUser = userRepository.findByUsername(username);
        if (currentUser==null){

            throw new UsernameNotFoundException(username);
        }
      Set<GrantedAuthority>ga = new HashSet<>();
        ga.add(new SimpleGrantedAuthority("Admin"));

        return new org.springframework.security.core.userdetails.User(currentUser.getUsername(),currentUser.getPassword(), ga) ;
    }
}
