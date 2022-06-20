package com.shubham.todolistapp.filters;

import com.shubham.todolistapp.data.TodoUser;
import com.shubham.todolistapp.services.implementation.UserDetailsServiceImpl;
import com.shubham.todolistapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
//        int id ;

        //this will check if header starts with "Bearer " and Header is provided
        if(header != null && header.startsWith("Bearer ")){

            //the actual token starts from 7th index position after Bearer
            jwt = header.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }


        //if username is not null, get the username & also SecurityContextHolder is empty
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            TodoUser todoUser = this.userDetailsService.loadUserByUsername(username);




            //if we get the token we need to validate it using jwt & username
            if(jwtUtil.validateToken(jwt, todoUser)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(todoUser, null, new ArrayList<>());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }

}
