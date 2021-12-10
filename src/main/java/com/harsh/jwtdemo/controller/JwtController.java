package com.harsh.jwtdemo.controller;

import com.harsh.jwtdemo.helper.JwtUtil;
import com.harsh.jwtdemo.model.JwtRequest;
import com.harsh.jwtdemo.model.JwtResponse;
import com.harsh.jwtdemo.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest){
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException e){
            e.printStackTrace();
        }
        // fine area
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

}
