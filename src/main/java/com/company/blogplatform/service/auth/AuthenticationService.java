package com.company.blogplatform.service.auth;

import com.company.blogplatform.dto.request.RefreshTokenRequest;
import com.company.blogplatform.dto.request.SignInRequest;
import com.company.blogplatform.dto.request.SignupRequest;
import com.company.blogplatform.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignupRequest signupRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
