package com.smida.service.service.auth;

import com.smida.service.model.User;
import com.smida.service.security.AuthenticationRequest;
import com.smida.service.security.AuthenticationResponse;
import com.smida.service.security.RegisterRequest;


public interface AuthenticationService {
    User register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
