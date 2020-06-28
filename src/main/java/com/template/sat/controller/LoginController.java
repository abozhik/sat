package com.template.sat.controller;

import com.template.sat.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController {

    @Qualifier("authenticationManager")
    private final AuthenticationManager authenticationManager;

    @PostMapping(value = "/api/login")
    public Object login(@RequestBody User user) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getLogin(), user.getPassword());
        User userDetails = new User();
        userDetails.setLogin(user.getLogin());
        token.setDetails(userDetails);

        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth.getPrincipal();
        } catch (BadCredentialsException e) {
            return "Wrong login or password";
        } catch (DisabledException e) {
            return "User id disabled";
        }
    }

    @PostMapping(value = "/api/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @GetMapping(value = "/api/loggedin")
    public Object loggedIn() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}