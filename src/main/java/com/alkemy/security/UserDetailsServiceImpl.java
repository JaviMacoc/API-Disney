package com.alkemy.security;

import com.alkemy.models.User;
import com.alkemy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        
        if(user != null){
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(false);            
            builder.password(user.getPassword());            
            
        }else
         {
            throw new UsernameNotFoundException("Los datos ingresados son inválidos");
        }
       
        return builder.build();
        
    }

}
