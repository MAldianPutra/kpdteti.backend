package edu.kpdteti.backend.security;

import edu.kpdteti.backend.entity.User;
import edu.kpdteti.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(userEmail);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email " + userEmail);
        }
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findByUserId(id);
        if (user == null) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        return UserPrincipal.create(user);
    }

}
