package com.example.playlist_app.service;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyService {

    private final String clientId = "3f51f2be359f453abad2c9cc652ee054";
    private final String clientSecret = "a3b38e59cd64470791e7ee37abe7083a";
    private final String authUrl = "https://accounts.spotify.com/api/token";
    private final String genresUrl = "https://api.spotify.com/v1/recommendations/available-genre-seeds";

    private String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(authUrl, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            return jsonResponse.getString("access_token");
        } else {
            throw new RuntimeException("Error obteniendo el token de Spotify: " + response.getStatusCode());
        }
    }

    public String getGenres() {
        RestTemplate restTemplate = new RestTemplate();

        String accessToken = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(genresUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error obteniendo los géneros de Spotify: " + response.getStatusCode());
        }
    }
}


