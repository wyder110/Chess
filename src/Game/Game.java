package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game {
    private Board board;
    public Game(){
        this.board = new Board();
        try {
            board.visualise();
            board.setPieces();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
