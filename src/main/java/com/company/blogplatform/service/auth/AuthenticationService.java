package com.company.blogplatform.service.auth;

import com.company.blogplatform.dto.JwtAuthenticationResponse;
import com.company.blogplatform.dto.RefreshTokenRequest;
import com.company.blogplatform.dto.SignInRequest;
import com.company.blogplatform.dto.SignupRequest;
import com.company.blogplatform.model.users.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
