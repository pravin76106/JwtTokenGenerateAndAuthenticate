package com.jwt.jwtToken;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public ResponseEntity<String> wellcome()
	{
		String response="Congratulation you are authenticated";
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
