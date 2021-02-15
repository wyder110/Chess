package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class Bishop extends Piece{
    public Bishop(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-bishop.png";
        }
        else{
            this.imagePath = "./img/white-bishop.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        if(this.tile.isDiagonal(tile)){
            if(!board.isSomethingBetween(this.tile,tile)){
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
