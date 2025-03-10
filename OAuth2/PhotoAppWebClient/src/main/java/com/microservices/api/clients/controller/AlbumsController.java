package com.microservices.api.clients.controller;


import com.microservices.api.clients.model.AlbumResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/albums")
@AllArgsConstructor
public class AlbumsController {

    private final OAuth2AuthorizedClientService clientService;

    private final RestTemplate restTemplate;

    @GetMapping
    public List<AlbumResponseModel> getAlbums(@AuthenticationPrincipal OidcUser principal) {
        System.out.println("principal: " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue: " + idTokenValue);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient = clientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());

        String jwtAccessToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken: " + jwtAccessToken);

        String url = "http://localhost:8082/albums";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        HttpEntity<List<AlbumResponseModel>> entity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumResponseModel>> listResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
                });

        return listResponseEntity.getBody();

    }

}
