package Game;

import Game.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    public int x, y;
    private JButton button;
    public Piece piece;
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean isDiagonal(Tile tile){
        if(this.equals(tile)) return false;
        return Math.abs(tile.x - x) == Math.abs(tile.y - y);
    }
    public boolean isInLine(Tile tile){
        if(this.equals(tile)) return false;
        return tile.x == x || tile.y == y;
    }
    public boolean isInLineOrDiagonal(Tile tile){
        if(this.equals(tile)) return false;
        return isDiagonal(tile) || isDiagonal(tile);
    }
    public boolean isKnightsMove(Tile tile){
        return ((Math.abs(tile.x - x) == 1) && (Math.abs(tile.y - y) == 2)) || ((Math.abs(tile.x - x) == 2) && (Math.abs(tile.y - y) == 1));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return x == tile.x && y == tile.y;
    }
    public void setButton(JButton button){
        this.button = button;

    }
    public void visualise() throws IOException {
        if(this.piece == null) {
            this.button.setIcon(null);
//            return;
        }
        else{
            Image img = ImageIO.read(new File(this.piece.imagePath));
            img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            this.button.setIcon(new ImageIcon(img));
        }
//        System.out.println("VISUAL " + this.button.toString());

    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
