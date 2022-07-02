package com.shubham.todolistapp.filters;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.services.implementation.UserDetailsServiceImpl;
import com.shubham.todolistapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String username = null;
        String jwt = null;


        //this will check if header starts with "Bearer " and Header is provided
        if(header != null && header.startsWith("Bearer ")){

            //storing the jwt in the jwt variable, coz the actual token will start from 7th index after bearer
            jwt = header.substring(7);

            //extracting username from jwt token
            username = jwtUtil.extractUsername(jwt);
        }

        //now we have to check whether any user is already logged in or not
        //this will be achieved using Security Context holder
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            //here TodoUser is a Principal storing all the credentials of logged-in user
            TodoUser todoUser = this.userDetailsService.loadUserByUsername(username);


            //if we get the token we need to validate it using jwt & username
            if(jwtUtil.validateToken(jwt, todoUser)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(todoUser, null, new ArrayList<>());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //from this username password authentication token we will add new user to security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }

}
