package com.example.playlist_app.controller;

import com.example.playlist_app.model.Playlist;
import com.example.playlist_app.service.PlaylistService;
import com.example.playlist_app.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/lists")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);
    private final PlaylistService playlistService;
    private final SpotifyService spotifyService;

    @Autowired
    public PlaylistController(PlaylistService playlistService, SpotifyService spotifyService) {
        this.playlistService = playlistService;
        this.spotifyService = spotifyService;
    }

    @PostMapping("/add")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody Playlist playlist,
                                                @RequestHeader("Authorization") String token) {
        HttpHeaders headers = new HttpHeaders();
        logger.info("Token recibido: {}", token);
        logger.info("Playlist recibida: {}", playlist);

        if (playlist.getName() == null || playlist.getName().isEmpty()) {
            return new ResponseEntity<>(null, headers, HttpStatus.BAD_REQUEST);
        }

        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return new ResponseEntity<>(savedPlaylist, headers, HttpStatus.CREATED);
    }


    @GetMapping("/genres")
    public ResponseEntity<String> getSpotifyGenres() {
        String genres = spotifyService.getGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/getPlaylist")
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/getBy/{listName}")
    public ResponseEntity<Playlist> getPlaylist(@PathVariable String listName) {
        Playlist playlist = playlistService.getPlaylistByName(listName);
        return ResponseEntity.ok(playlist);
    }

    @DeleteMapping("/delete/{listName}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable String listName) {
        playlistService.deletePlaylist(listName);
        return ResponseEntity.noContent().build();
    }
}
