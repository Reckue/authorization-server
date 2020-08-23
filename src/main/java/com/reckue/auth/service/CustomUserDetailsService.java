package com.reckue.auth.service;

import com.reckue.auth.model.Role;
import com.reckue.auth.model.Status;
import com.reckue.auth.model.User;
import com.reckue.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Class CustomUserDetailsService contains a single method of interface UserDetailsService.
 *
 * @author Kamila Meshcheryakova
 */
@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * This method is used to locate the user based on the username.
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated user record
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User by username '" + username + "' not found"));
        Set<Role> set =  new HashSet<>();
        set.add(new Role("ROLE_USER"));
            User user = new User("1", Status.ACTIVE, "user", "string@mail.ru", "password",
                   set, 1598213648,
                    1598213648,1598213648);
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
