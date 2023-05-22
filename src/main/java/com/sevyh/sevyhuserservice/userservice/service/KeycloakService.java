package com.sevyh.sevyhuserservice.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.keycloak.OAuth2Constants;


import jakarta.annotation.PostConstruct;

@Service
public class KeycloakService {

    @Value("${keycloak.credentials.secret}")
    private String secret;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String resource;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;

    private Keycloak keycloakInstance;

    @PostConstruct
    public void init() {
        this.keycloakInstance = KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(resource)
                .clientSecret(secret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .resteasyClient(ResteasyClientBuilder.newBuilder().build())
                .build();
    }

    public List<String> getUserUuids() {
        return this.keycloakInstance.realm(realm)
                .users()
                .list()
                .stream()
                .map(UserRepresentation::getId)
                .collect(Collectors.toList());
    }
}
