package com.example.userservice.application.service;

import com.example.userservice.persistence.domain.Token;

public interface TokenService {
     void deleteRefreshToken(String memberKey);
     String saveOrUpdate(String userKey, String refreshToken, String accessToken);
     Token findByAccessTokenOrThrow(String accessToken);
     void updateToken(String accessToken, Token token);
}
