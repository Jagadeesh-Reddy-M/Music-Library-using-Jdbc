package com.example.song.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.song.model.Song;
import com.example.song.service.SongH2Service;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class SongController{
    @Autowired
    public SongH2Service songsevice;
    @GetMapping("/songs")
    public ArrayList<Song> getSongsall(){
        return songsevice.getSongs();
    }
    @GetMapping("/songs/{songId}")
    public Song getSongsallById(@PathVariable ("songId") int songId){
        return songsevice.getSongsById(songId);
    }
    @PostMapping("/songs")
    public Song addSongpostall(@RequestBody Song song){
        return songsevice.addSong(song);
    }
    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable ("songId") int songId, @RequestBody Song song){
        return songsevice.updateSong(songId , song);
    }
    @DeleteMapping("/songs/{songId}")
    public void deleteSongsbyId(@PathVariable ("songId") int songId){
        songsevice.deleteSong(songId);
    }

}