package com.itlize.korera.Controller;
import com.itlize.korera.Entities.User;
import com.itlize.korera.Security.*;
import com.itlize.korera.Service.AuthService;
import com.itlize.korera.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {



    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    //    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        if (userService.findByUsername(user.getUsername()) != null) {
//            return new ResponseEntity<>("the user is already existed",HttpStatus.CONFLICT);
//        }
//        user.setRole(Role.USER);
//        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.CREATED);
//    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if(this.authService.isExsited(request)){
            return new ResponseEntity<>("the user is already existed",HttpStatus.CONFLICT);
        }
        System.out.println(request);
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {

        return ResponseEntity.ok(authService.authenticate(request));
    }
}




//    @GetMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody User User) throws Exception {
//
//        try {
//            myauthenticaitonManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(User.getUsername(), User.getPassword())
//            );
//        }
//        catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//
//
//        final UserDetails userDetails = applicationConfig.userDetailsService().loadUserByUsername(User.getUsername());
//
//        final String jwt = jwtService.generateToken(userDetails);
//
//        return  new ResponseEntity<>(jwt, HttpStatus.OK);
//    }
//}
