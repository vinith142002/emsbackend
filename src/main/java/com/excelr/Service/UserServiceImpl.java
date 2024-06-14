package com.excelr.Service;


import com.excelr.Repo.UserRepository;
import com.excelr.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<User> userOptional = userRepository.findFirstByEmail(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (user.isAccountNonLocked()) {
                        throw new LockedException("User account is locked");
                    }
                    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
                } else {
                    throw new UsernameNotFoundException("User not found");
                }
            }
        };
    }
}