package com.smida.service.service.auth;

import com.smida.service.exception.NoSuchUserException;
import com.smida.service.model.User;
import com.smida.service.repository.UserRepository;
import com.smida.service.security.AuthenticationRequest;
import com.smida.service.security.AuthenticationResponse;
import com.smida.service.security.JwtService;
import com.smida.service.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Transactional
    @Override
    public User register(RegisterRequest request) {
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getRole());
        logger.info("User with username {} trying to register", user.getUsername());
        checkNewUsername(user.getUsername());
        return userRepository.save(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        logger.info("User with username {} trying to authenticate", request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception ex) {
            throw new RuntimeException("Authentication failed: " + ex.getMessage());
        }
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NoSuchUserException("Invalid username"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Trying to login with incorrect password");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    private void checkNewUsername(String username) {
        if (userRepository.existsByUsername(username))
            throw new RuntimeException("This username is already exist");
    }
}