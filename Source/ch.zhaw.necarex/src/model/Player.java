/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Ein Schachspieler (Player) besitzt eine Farbe. 
 * @author florian
 */
public class Player {
    private PlayerColor color;
    
    /**
     * Erstellt einen Spieler
     * @param Weiss oder Schweiz (enum)
     */
    public Player(PlayerColor color){
        this.color = color;
    }

    /**
     * Gibt die Farbe des Spielers
     * @return Farbe des Spielers
     */
    public PlayerColor getColor() {
        return color;
    }
}
