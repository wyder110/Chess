package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class Knight  extends Piece {
    public Knight(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-knight.png";
        }
        else{
            this.imagePath = "./img/white-knight.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        if(this.tile.isKnightsMove(tile)){
            return true;
        }
        return false;
    }

    @Override
    public boolean attacks(Tile tile) {
        return this.tile.isKnightsMove(tile);
    }
}
