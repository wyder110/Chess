package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public class King extends Piece {


    public King(Tile tile, Color color, Board board) {
        super(tile, color, board);
        if(color == Color.BLACK){
            this.imagePath = "./img/black-king.png";
        }
        else{
            this.imagePath = "./img/white-king.png";
        }
    }

    @Override
    public boolean canMoveTo(Tile tile) {
        Color oppositeColor = this.color == Color.WHITE ? Color.BLACK : Color.WHITE;
        System.out.println("Color " + this.color + " oposite " + oppositeColor + " is attacked " + board.tileIsAttacked(tile,oppositeColor));
        return (Math.abs(this.tile.x - tile.x) <= 1 && Math.abs(this.tile.y - tile.y) <= 1 && !board.tileIsAttacked(tile,oppositeColor));
    }

    @Override
    public boolean attacks(Tile tile) {
        return (Math.abs(this.tile.x - tile.x) <= 1 && Math.abs(this.tile.y - tile.y) <= 1);
    }
}
