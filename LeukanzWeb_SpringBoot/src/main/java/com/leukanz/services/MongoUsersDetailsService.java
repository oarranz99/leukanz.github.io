 package com.leukanz.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.leukanz.domain.Users;
import com.leukanz.mongo.repository.UsersRepository;

@Component
@Qualifier("MongoUsersDetailsService")
public class MongoUsersDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository repository;
	@Autowired 
	private PasswordEncoder pswdEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority> authorities;
		Users user = repository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		if(user.getUsername() == "admin") {
			authorities = Arrays.asList(new SimpleGrantedAuthority("admin"));
		}else {
			authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
		}
		
		return new User(user.getUsername(), user.getPassword(),authorities);
	}
	
	public Users saveUser(Users user) {
		user.setPassword(pswdEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

}
