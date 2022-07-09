package com.jwt.jwtToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailService userDetailService;

	@Autowired
	JwtUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
		} 
		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential");
		}
	    UserDetails userDetails= this.userDetailService.loadUserByUsername(jwtRequest.getUserName(),jwtRequest.getPassword());
	  
	    String Token=this.jwtUtil.generateToken(userDetails);
	   
	   
	     return new ResponseEntity<>(new JwtResponse(Token),HttpStatus.OK);
	}
}
