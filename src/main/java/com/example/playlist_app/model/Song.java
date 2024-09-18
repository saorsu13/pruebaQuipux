package com.example.playlist_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue

    private Long id;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int releaseYear;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Playlist playlist;
}
