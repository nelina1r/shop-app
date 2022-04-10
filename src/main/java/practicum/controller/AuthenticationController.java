package practicum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import practicum.controller.url.Urls;
import practicum.dto.AuthenticationRequestDto;
import practicum.model.entity.User;
import practicum.security.jwt.JwtTokenProvider;
import practicum.service.interfaces.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(Urls.Auth.Login.FULL)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
            if (userService.findByUsername(requestDto.getUsername()) == null) {
                throw new UsernameNotFoundException("User with username: " + requestDto.getUsername() + " not found");
            }
            return ResponseEntity.ok(Map.of("username", requestDto.getUsername(),
                                            "token", jwtTokenProvider.createToken(requestDto.getUsername())));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(Urls.Auth.Register.FULL)
    public ResponseEntity<String> register(@RequestBody User user){
        userService.register(user);
        return ResponseEntity.ok("registered");
    }
}
