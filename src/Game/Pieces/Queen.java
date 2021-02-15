package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class Queen extends Piece {


    public Queen(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-queen.png";
        }
        else{
            this.imagePath = "./img/white-queen.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        if(this.tile.isInLine(tile) || this.tile.isDiagonal(tile)){
//            System.out.println("this.tile " + this.tile + "tile"+tile);
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
