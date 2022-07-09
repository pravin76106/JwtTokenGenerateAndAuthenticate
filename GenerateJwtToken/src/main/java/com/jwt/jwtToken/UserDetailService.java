package com.jwt.jwtToken;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.equals("Pravin") )
        {
        	return new User("Pravin","12345",new ArrayList<>());
        }  
        else
        {
        	throw new UsernameNotFoundException("User not Found!!");
        }
	}

	public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException {

        if(username.equals("Pravin")&&password.equals("12345") )
        {
        	return new User("Pravin","12345",new ArrayList<>());
        }  
        else
        {
        	throw new UsernameNotFoundException("User not Found!!");
        }
	}


}
