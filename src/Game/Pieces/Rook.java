package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class Rook extends Piece {


    public Rook(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-rook.png";
        }
        else{
            this.imagePath = "./img/white-rook.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        System.out.println("isInLine " + this.tile.isInLine(tile));
        if(this.tile.isInLine(tile)){
            System.out.println("this.tile " + this.tile + "tile"+tile);
            if(!board.isSomethingBetween(this.tile,tile)){
                System.out.println("can move");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean attacks(Tile tile) {
        return canMoveTo(tile);
    }
}
