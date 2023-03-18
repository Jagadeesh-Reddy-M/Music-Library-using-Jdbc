package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.repository.SongRepository;
import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;
@Service
public class SongH2Service implements SongRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Song> getSongs(){
        List<Song> Songsall = db.query("select * from Playlist" , new SongRowMapper());
        ArrayList<Song> songs = new ArrayList<>(Songsall);
        return songs;
    }
    @Override
    public Song getSongsById(int songId){
        try{
            Song song = db.queryForObject("select * from Playlist where songId = ?", new SongRowMapper(),songId);
            return song;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Song addSong(Song song){
        // db.update("insert into Playlist(songName , lyricist,musicDirector , singer) values(song.getSongName(),song.getLyricist(),song.getMusicDirector(),song.getSinger())");
        // Song ps = db.queryForObject("select * from Playlist where songName = ? and lyricist = ? and musicDirector = ? and singer = ?",new SongRowMapper(),song.getSongName(),song.getLyricist(),song.getMusicDirector(),song.getSinger());
        // return ps;
        String sql = "INSERT INTO playlist (songName, lyricist,musicDirector,singer) VALUES (?, ? ,? ,?)";

    // Use the JdbcTemplate to execute the SQL statement with the book data
        db.update(sql, song.getSongName(), song.getLyricist() , song.getMusicDirector() , song.getSinger());
        Song ps = db.queryForObject("select * from playlist where songName = ? and lyricist = ? and musicDirector = ? and singer = ?",new SongRowMapper(),song.getSongName(),song.getLyricist(),song.getMusicDirector(),song.getSinger());
        return ps;
    }
    @Override
    public Song updateSong(int songId , Song song){
        if(song.getSongName()!=null){
            db.update("update playlist set songName = ? where songId = ?",song.getSongName() , songId);
        }
        if(song.getLyricist()!=null){
            db.update("update playlist set lyricist = ? where songId = ?",song.getLyricist() , songId);
        }
        if(song.getMusicDirector()!=null){
            db.update("update playlist set musicDirector = ? where songId = ?",song.getMusicDirector() , songId);
        }
        if(song.getSinger()!=null){
            db.update("update playlist set singer = ? where songId = ?",song.getSinger() , songId);
        }
        return getSongsById(songId);
    }
    @Override
    public void deleteSong (int songId){
        db.update("delete from playlist where songId = ?",songId);
    }

}