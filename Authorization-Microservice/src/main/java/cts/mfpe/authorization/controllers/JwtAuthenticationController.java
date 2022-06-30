package cts.mfpe.authorization.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.authorization.configs.JwtTokenUtil;
import cts.mfpe.authorization.entities.JwtRequest;
import cts.mfpe.authorization.entities.JwtResponse;
import cts.mfpe.authorization.entities.User;
import cts.mfpe.authorization.exceptions.AuthorizationException;
import cts.mfpe.authorization.services.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws AuthorizationException {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private Authentication  authenticate(String username, String password) throws AuthorizationException {
		try {
			Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return auth;
		} catch (DisabledException e) {
			throw new AuthorizationException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new AuthorizationException("INVALID_CREDENTIALS");
		}
	}

	@PostMapping(value = "/register")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws Exception {
		userDetailsService.save(user);
		return new ResponseEntity<String>("User Created Successfully!",HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		String jwtToken = null;
		String userName = null;
		boolean flag = false;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return flag;
			}
		}
		User user = userDetailsService.getUserByName(userName);
		List<String> roles = user.getRoles().stream()
		        .map(role -> role.getName())
		        .collect(Collectors.toList());
		
		if(roles.contains("ROLE_MANAGER") || roles.contains("ROLE_EXECUTIVE") || roles.contains("ROLE_CUSTOMER")) {
			flag = true;
		}
		
		return flag;

	}
	
	@PostMapping(value = "/authorize-manager")
	public boolean authorizeTheRequestForManager(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		String jwtToken = null;
		String userName = null;
		boolean flag = false;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return flag;
			}
		}
		User user = userDetailsService.getUserByName(userName);
		List<String> roles = user.getRoles().stream()
		        .map(role -> role.getName())
		        .collect(Collectors.toList());
		
		if(roles.contains("ROLE_MANAGER")) {
			flag = true;
		}
		
		return flag;
	}
	
	@PostMapping(value = "/authorize-executive")
	public boolean authorizeTheRequestForExecutive(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		String jwtToken = null;
		String userName = null;
		boolean flag = false;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return flag;
			}
		}
		User user = userDetailsService.getUserByName(userName);
		List<String> roles = user.getRoles().stream()
		        .map(role -> role.getName())
		        .collect(Collectors.toList());
		
		if(roles.contains("ROLE_EXECUTIVE")) {
			flag = true;
		}
		
		return flag;
	}
	
	@PostMapping(value = "/authorize-customer")
	public boolean authorizeTheRequestForCustomer(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) {
		String jwtToken = null;
		String userName = null;
		boolean flag = false;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException | ExpiredJwtException e) {
				return flag;
			}
		}
		User user = userDetailsService.getUserByName(userName);
		List<String> roles = user.getRoles().stream()
		        .map(role -> role.getName())
		        .collect(Collectors.toList());
		
		if(roles.contains("ROLE_CUSTOMER")) {
			flag = true;
		}
		
		return flag;
	}
	
}
