package com.sis.StudentInfoSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sis.StudentInfoSystem.security.CustomUserDetailsService;
import com.sis.StudentInfoSystem.security.JwtRequest;
import com.sis.StudentInfoSystem.security.JwtResponse;
import com.sis.StudentInfoSystem.security.JwtUtil;

@CrossOrigin("*")
@RestController
public class JwtController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try 
		{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );
        } 
		catch(UsernameNotFoundException e) {
			throw new Exception("Username not found");
		}
		catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
		
		//fine area
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());
		
		String token = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
