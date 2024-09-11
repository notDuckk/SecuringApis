package org.example.securingapis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());

        return new JwtResponse(token);
    }
}