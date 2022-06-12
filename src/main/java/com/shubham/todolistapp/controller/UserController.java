package com.shubham.todolistapp.controller;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.entity.JwtRequest;
import com.shubham.todolistapp.entity.JwtResponse;
import com.shubham.todolistapp.entity.UserDto;
import com.shubham.todolistapp.services.implementation.UserDetailsServiceImpl;
import com.shubham.todolistapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/user")
public class UserController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;



    //hello url
    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }


    //authentication url
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception{

        //authentication part...
        try{
            //we are using authentication manger to authenticate using username & password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username & password", e);
        }


        //loading username in user details
        final TodoUser todoUser = userDetailsService
                .loadUserByUsername(jwtRequest.getUsername());

        //generating jwt response token
        final String jwt = jwtUtil.generateToken(todoUser);

        //returning JwtResponse token
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

}
