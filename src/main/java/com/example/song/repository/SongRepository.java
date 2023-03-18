package com.example.song.repository;
import java.util.*;
import com.example.song.model.Song;

public interface SongRepository{
    ArrayList<Song> getSongs();
    Song getSongsById(int SongId);
    Song addSong(Song song);
    Song updateSong(int songId , Song song);
    void deleteSong(int songId);
}