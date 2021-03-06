package com.practice.board.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.board.entity.refeshToken.RefreshToken;
import com.practice.board.entity.refeshToken.RefreshTokenRepository;
import com.practice.board.entity.user.GoogleUser;
import com.practice.board.security.jwt.JwtProperties;
import com.practice.board.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final JwtProperties jwtProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        GoogleUser user = GoogleUser.builder()
                .oauthId(oAuth2User.getName())
                .username(oAuth2User.getAttributes().get("name").toString())
                .email(oAuth2User.getAttributes().get("email").toString())
                .build();

        RefreshToken refreshToken = refreshTokenRepository.save(
                RefreshToken.builder()
                        .uuid(String.valueOf(user.getUuid()))
                        .refreshToken(jwtTokenProvider.createRefreshToken(String.valueOf(user.getUuid())))
                        .expiration(jwtProperties.getRefresh())
                        .build());

        response.addHeader("Authorization", jwtTokenProvider.createAccessToken(String.valueOf(user.getUuid())));
        response.addHeader("X-Refresh-Token", refreshToken.getRefreshToken());
        getRedirectStrategy().sendRedirect(request, response, "/");
    }
}