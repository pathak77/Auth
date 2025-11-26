package com.prod.auth.Service;

import com.prod.auth.Entity.User;
import com.prod.auth.Repository.UserDetailRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    UserDetailRepo repo;

    public UserDetailService(UserDetailRepo repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repo.
                findUserByUsername(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return user.get();
    }

}
