package com.itlize.korera.Service;

import com.itlize.korera.Entities.Role;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Repositories.UserRepository;
import com.itlize.korera.Security.AuthenticationRequest;
import com.itlize.korera.Security.AuthenticationResponse;
import com.itlize.korera.Security.JwtService;
import com.itlize.korera.Security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Boolean isExsited(RegisterRequest request) {
        if(this.userRepository.existsUserByUsername(request.getUsername())){
            return true;
        }else{
            return false;
        }
    }

    public AuthenticationResponse register(RegisterRequest request) {
            System.out.println(request);
            User user = new User();
            user.setUsername(request.getUsername());
            user.setFname(request.getFname());
            user.setLname(request.getLname());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setCreated_date(new Date());
            user.setRole(Role.USER);
            this.userRepository.save(user);

            String jwtToken = jwtService.generateToken(user);

            return new AuthenticationResponse(jwtToken);


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        System.out.println(request);
//        try {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//        }catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
        User user = this.userRepository.findByUsername(request.getUsername());
        System.out.println(user);
        String jwtToken = jwtService.generateToken(user);
        System.out.println(jwtToken);
        return new AuthenticationResponse(jwtToken);

    }
}
