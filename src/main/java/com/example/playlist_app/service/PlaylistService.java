package com.example.playlist_app.service;

import com.example.playlist_app.model.Playlist;
import com.example.playlist_app.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public Playlist savePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistByName(String name) {
        return playlistRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
    }

    public void deletePlaylist(String name) {
        Playlist playlist = getPlaylistByName(name);
        playlistRepository.delete(playlist);
    }
}
