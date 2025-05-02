package com.example.userservice.application.service.impl;

import com.example.userservice.application.service.TokenService;
import com.example.userservice.commons.exception.TokenException;
import com.example.userservice.commons.exception.payload.ErrorStatus;
import com.example.userservice.persistence.domain.Token;
import com.example.userservice.persistence.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public void deleteRefreshToken(String memberKey) {
        tokenRepository.deleteById(memberKey);
    }

    public String saveOrUpdate(String userKey, String refreshToken, String accessToken) {

        Token token = tokenRepository.findByAccessToken(accessToken)
                .map(o -> o.updateRefreshToken(refreshToken))
                .orElseGet(() -> tokenRepository.save(Token.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .id(userKey)
                        .build()));

        tokenRepository.save(token);

        return refreshToken;
    }

    public Token findByAccessTokenOrThrow(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new TokenException(
                        ErrorStatus.toErrorStatus("토큰을 찾을 수 없습니다.", 401, LocalDateTime.now())));
    }

    @Transactional
    public void updateToken(String accessToken, Token token) {
        token.updateAccessToken(accessToken);
        tokenRepository.save(token);
    }
}
