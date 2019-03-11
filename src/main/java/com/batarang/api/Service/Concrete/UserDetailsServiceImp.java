package com.batarang.api.Service.Concrete;

import com.batarang.api.Model.CustomPrincipal;
import com.batarang.api.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    public UserDetailsServiceImp(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try{
            User user = userService.findByUserName(s);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new CustomPrincipal(user);

        }catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }

}