/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author florian
 */
public class Player {
    private PlayerColor color;
    
    public Player(PlayerColor color){
        this.color = color;
    }

    /**
     * @return the color
     */
    public PlayerColor getColor() {
        return color;
    }
    
}
