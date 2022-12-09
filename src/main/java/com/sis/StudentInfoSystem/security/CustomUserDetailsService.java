package com.sis.StudentInfoSystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import com.sis.StudentInfoSystem.Models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sis.StudentInfoSystem.Repository.UserRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService  implements  UserDetailsService {

	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
//		if(email.equals("admin@gmail.com")) 
//		{
//			return new User("admin@gmail.com","$2a$12$lJI4I2blM2fcEoJFR82h2.p1rRRjzXoTrzA/mEDmhrkRcerwUdtOC"
//					,new ArrayList<>());
//		}
//		else {
//			throw new UsernameNotFoundException("User not found!!");
//		}
		
		
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			return user;
		}
		else {
			throw new UsernameNotFoundException("User not found!!");
		}
		
	}

}
