/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsongrecordsystem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Enes
 */
public class MusicSongRecordSystem {


    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String BLUE_BACKGROUND = ("\u001B[44m");
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED =("\033[0;31m");
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileInputStream songs = new FileInputStream("songs.txt");
        Scanner scn = new Scanner(songs);
        
        int lines = 0;// Array sizeı için kaç adet şarkı olduğunu hesaplıyoruz
         BufferedReader songsLine = new BufferedReader(new FileReader("songs.txt"));
         String line = songsLine.readLine();
             while (line!=null) {
              if(line.length()>0){
               lines++;
              }
              line = songsLine.readLine();                
             }
             
        Song songsArray[] = new Song[lines];//Şarkıları depolayan array
        Song song = null;

        int songİndex=0;
        while(scn.hasNext()){//array içine şarkılar atılıyor
            String a = scn.nextLine();
            String[] textParts = a.split(";");
            String name = textParts[0];
            String artist = textParts[1];
            int id = Integer.parseInt(textParts[2]);
            String genre = textParts[3];
            int year = Integer.parseInt(textParts[4]);
            song = new Song(name, artist, id, genre, year);
            songsArray[songİndex] = song;
            songİndex++;
            
        }
        scn.close();
        
        AVLTree NameTree = new AVLTree();
        AVLTree IdTree = new AVLTree();
        AVLTree ArtistTree = new AVLTree();
        
        NameTree.root = NameTree.generate(songsArray, NameTree, 1);//özelliklere göre ağaçlar oluşturuluyor
        IdTree.root = IdTree.generate(songsArray, IdTree, 2);
        ArtistTree.root = ArtistTree.generate(songsArray, ArtistTree, 3);
        /*System.out.println(NameTree.getBalance(NameTree.root));
        System.out.println(IdTree.getBalance(IdTree.root));
        System.out.println(ArtistTree.getBalance(ArtistTree.root));
        NameTree.postOrder(NameTree.root);
        System.out.println("--------------");
        IdTree.postOrder(IdTree.root);
        System.out.println("--------------");
        ArtistTree.postOrder(ArtistTree.root);*/
        
        
        for (int i = 0; i < songsArray.length; i++) {//Şarkıları Bastırıyor
            System.out.println(songsArray[i].toString());
        }
        
        Scanner input1 = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.print(
                    "\nWhich task you want to do? \n"
                    + "1. First Search Method.\n"
                    + "2. Third Search Method\n"
                    + "3. Delete song based on id.\n"
                    + "4. Quit\n"
            );
            
            int seçim = input.nextInt();
            switch (seçim) {
                case 1:
                    System.out.println(YELLOW+"Which one do you want to search with?: 1.Name 2.Id 3.Artist"+RESET);
                    int searchNumber = input.nextInt();
                    
                    switch (searchNumber) {
                        case 1:
                            System.out.println(YELLOW + "Enter a song name: /all first letter capital/" + RESET);
                            String songName = input1.nextLine();
                            
                            System.out.println("");
                            System.out.println(YELLOW + "Method to print first find: "+ RESET);
                            Node nameResults1 = NameTree.searchString(NameTree.root, songName);
                            if(nameResults1==null){
                                System.out.println(RED + "There is no song named " + songName + RESET);
                            }else{
                                System.out.println(songsArray[nameResults1.getData()].toString());
                            }
                            
                        //Hepsini Bastıran Kod Ve Arama
                            /*System.out.println("");
                            System.out.println(YELLOW +"Method to print all results in the entered " + songName+ RESET );
                            ArrayList<Node> nameResults = new ArrayList();
                            nameResults = NameTree.search2(NameTree.root, songName);
                            if(nameResults.isEmpty()){
                                System.out.println(RED +"There is no song named " + songName + RESET);
                            }else{
                                for (int i = 0; i < nameResults.size(); i++) {
                                    System.out.println(songsArray[nameResults.get(i).getData()].toString());
                                }
                            }*/
                            break;
                        case 2:
                            System.out.println(YELLOW +"Enter a song id:"+ RESET);
                            int songId = input.nextInt();
                            
                            System.out.println("");
                            System.out.println(YELLOW + "Method to print first find: "+ RESET);
                            Node IDresult = IdTree.searchInteger(IdTree.root, songId);
                            if(IDresult==null){
                                System.out.println(RED+"No song found with id " + songId+RESET);
                            }else{
                                System.out.println(songsArray[IDresult.getData()].toString());
                            }
                            break;
                        case 3:
                            System.out.println(YELLOW+"Enter a artist name: /all first letter capital/"+ RESET);
                            String artistName = input1.nextLine();
                            
                            System.out.println("");
                            System.out.println(YELLOW + "Method to print first find: "+ RESET);
                            Node Artistresult = ArtistTree.searchString(ArtistTree.root, artistName);
                            if(Artistresult==null){
                                System.out.println(RED+artistName + " does not have any songs in the list"+ RESET);
                            }else{
                                System.out.println(songsArray[Artistresult.getData()].toString());
                            }
                            
                        //Hepsini Bastıran Kod Ve Arama
                            /*System.out.println("");
                            System.out.println(YELLOW +"Method to print all results in the entered " + artistName+ RESET );
                            ArrayList<Node> artistResults = new ArrayList();
                            artistResults = ArtistTree.search2(ArtistTree.root, artistName);
                            if(artistResults.isEmpty()){
                                System.out.println(RED+artistName + " does not have any songs in the list"+ RESET);
                            }else{
                                for (int i = 0; i < artistResults.size(); i++) {
                                    System.out.println(songsArray[artistResults.get(i).getData()].toString());
                                }
                            }*/
                            break;
                        default:
                            System.out.println(RED+ "Please choose a valid task number." + RESET);
                            break;
                            }
                    break;
                case 2:
                    System.out.println(YELLOW+"Enter songs id: /first lower/"+ RESET);
                            int lower = input.nextInt();
                            int higher = input.nextInt();
                            ArrayList<Node> idResults = new ArrayList();
                            idResults = IdTree.search3(IdTree.root, lower, higher);
                            if(idResults.isEmpty()){
                                System.out.println(RED+"There are no songs in the id range you entered " + lower + ", " + higher+ RESET);
                            }else{
                                System.out.println("");
                                for (int i = 0; i < idResults.size(); i++) {
                                    System.out.print(YELLOW+"Id: "+RESET);
                                    System.out.println(idResults.get(i).key);
                                    System.out.println(songsArray[idResults.get(i).getData()].toString());
                                    System.out.println("");
                                }
                            }
                            break;
                case 3:
                    for (int i = 0; i < songsArray.length; i++) {
                        System.out.println(songsArray[i].toString());
                    }
                    System.out.println("");
                    System.out.println(YELLOW+"Enter the id you want to delete: "+RESET);
                    int deleteId = input.nextInt();
                    
                    Node deleted = IdTree.searchInteger(IdTree.root, deleteId);
                    //System.out.println(deleted.data + " " + deleted.key);
                    IdTree.deleteId(IdTree.root, (int)deleted.key);
                    
                    NameTree.deleteString(NameTree.root, songsArray[deleted.data].getName());
                    ArtistTree.deleteString(ArtistTree.root, songsArray[deleted.data].getArtist());
                    System.out.println(YELLOW+"The song in the id you entered has been deleted from all trees"+RESET);
                    /*NameTree.postOrder(NameTree.root);
                    System.out.println("--------------");
                    IdTree.postOrder(IdTree.root);
                    System.out.println("--------------");
                    ArtistTree.postOrder(ArtistTree.root);*/
                    break;
                case 4:
                    System.out.println("Exiting...");
                    loop = false;
                    break;  
                default:
                    System.out.println(RED+"Please choose a valid task number."+RESET);
                    break;
            }
        }
    }
}
            
