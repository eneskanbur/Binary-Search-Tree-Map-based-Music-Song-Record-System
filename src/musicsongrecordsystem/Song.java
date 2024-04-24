/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsongrecordsystem;

/**
 *
 * @author Enes
 */
public class Song {
    private String name;
    private String artist;
    private int id;
    private String genre;
    private int year;
    
    public Song(String name, String artist, int id, String genre, int year) {
        this.name = name;
        this.artist = artist;
        this.id = id;
        this.genre = genre;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return ("Name: " + name + " Artist: " + artist + " ID: " + id + " Genre: " + genre + " Year: " + year);
    }

    
    
    
}
