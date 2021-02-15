package Game.Pieces;

import Game.Board;
import Game.Tile;

import java.awt.*;

public abstract class Piece {
    protected Tile tile;
    public String imagePath;
    protected Board board;
    public Color color;

    public Piece(Tile tile, Color color, Board board){
        this.tile = tile;
        tile.piece = this;
        this.color = color;
        this.board = board;
    }

    public abstract boolean canMoveTo(Tile tile);
    public abstract boolean attacks(Tile tile);
    public void move(Tile tile){
        this.tile.piece = null;
        this.tile = tile;
        this.tile.piece = this;

    }
}
