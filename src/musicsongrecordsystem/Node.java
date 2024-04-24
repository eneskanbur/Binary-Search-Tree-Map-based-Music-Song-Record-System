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
public class Node<T>{
    
    int data;
    T key;
    Node right;
    Node left;
    int height;

    public Node(int data, T key) {
        this.data = data;
        this.key = key;
        height = 1;
    }

    public int getData() {
        return data;
    }
    
}
