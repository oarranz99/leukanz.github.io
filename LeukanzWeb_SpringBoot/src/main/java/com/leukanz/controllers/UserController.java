package com.leukanz.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leukanz.domain.Users;
import com.leukanz.services.MongoUsersDetailsService;

@RestController
@RequestMapping("api/users")
public class UserController {
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Autowired
		private MongoUsersDetailsService usersDetailsService;
		@Autowired
		private PasswordEncoder pswdEncoder;

	    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	    public UserDetails getUserByEmail(@RequestParam String username) {
	        return usersDetailsService.loadUserByUsername(username);
	    }

	    @RequestMapping(value = "/save}", method = RequestMethod.POST)
	    public boolean save(@RequestBody Users user) {
	    	usersDetailsService.saveUser(new Users(user.getUsername() , pswdEncoder.encode(user.getPassword())));
	      return true;
	    } 
}
