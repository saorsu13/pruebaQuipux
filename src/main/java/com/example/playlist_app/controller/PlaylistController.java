package com.example.playlist_app.controller;

import com.example.playlist_app.model.Playlist;
import com.example.playlist_app.service.PlaylistService;
import com.example.playlist_app.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;
    private SpotifyService spotifyService;


    @PostMapping("/add")
    public ResponseEntity<Playlist> addPlaylist(@RequestBody Playlist playlist) {
        if (playlist.getName() == null || playlist.getName().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Playlist savedPlaylist = playlistService.savePlaylist(playlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlaylist);
    }

    @GetMapping("/genres")
    public ResponseEntity<String> getSpotifyGenres(@RequestHeader("Authorization") String token) {
        String genres = spotifyService.getGenres(token);
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
