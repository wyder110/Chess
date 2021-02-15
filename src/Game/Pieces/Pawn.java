package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class Pawn extends Piece {
    public Pawn(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-pawn.png";
        }
        else{
            this.imagePath = "./img/white-pawn.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        //TODO en pessant
        if(color == Color.WHITE){

            if(tile.y == this.tile.y+1 && tile.x == this.tile.x && tile.piece == null){
                return true;
            }
            else if(tile.y == this.tile.y+2 && this.tile.y == 1 && tile.x == this.tile.x && tile.piece == null){
                return true;
            }
            else if(tile.y == this.tile.y+1 && tile.x+1 == this.tile.x){
                if(tile.piece != null && tile.piece.color == Color.BLACK){
                    return true;
                }
            }
            else if(tile.y == this.tile.y+1 && tile.x-1 == this.tile.x){
                if(tile.piece != null && tile.piece.color == Color.BLACK){
                    return true;
                }
            }
        }
        if(color == Color.BLACK){
            if(tile.y == this.tile.y-1 && tile.x == this.tile.x && tile.piece == null){
                return true;
            }
            else if(tile.y == this.tile.y-2 && this.tile.y == 6 && tile.x == this.tile.x && tile.piece == null){
                return true;
            }
            else if(tile.y == this.tile.y-1 && tile.x+1 == this.tile.x){
                if(tile.piece != null && tile.piece.color == Color.WHITE){
                    return true;
                }
            }
            else if(tile.y == this.tile.y-1 && tile.x-1 == this.tile.x){
                if(tile.piece != null && tile.piece.color == Color.WHITE){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean attacks(Tile tile) {
        if(color == Color.WHITE){
            if(tile.y == this.tile.y+1 && tile.x+1 == this.tile.x){
                return true;
            }
            else if(tile.y == this.tile.y+1 && tile.x-1 == this.tile.x){
                return true;
            }
        }
        if(color == Color.BLACK){
            if(tile.y == this.tile.y-1 && tile.x+1 == this.tile.x){
                return true;
            }
            else if(tile.y == this.tile.y-1 && tile.x-1 == this.tile.x){
                return true;
            }
        }
        return false;
    }
}
