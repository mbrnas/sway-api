package com.company.blogplatform.service.auth;

import com.company.blogplatform.dto.JwtAuthenticationResponse;
import com.company.blogplatform.dto.RefreshTokenRequest;
import com.company.blogplatform.dto.SignInRequest;
import com.company.blogplatform.dto.SignupRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignupRequest signupRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
