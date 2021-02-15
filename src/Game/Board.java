package Game;


import Game.Pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private Tile tiles[][];
    private Tile tileClicked;
    ArrayList<Piece> whitePieces, blackPieces;
    Color turnOrder;
    public Board(){

        tiles = new Tile[8][8];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                tiles[y][x] = new Tile(x,y);
            }
        }
        turnOrder = Color.WHITE;

    }
    public static boolean outOfBounds(int numb){
        return 0 <= numb && numb <= 7;
    }

    public void setPieces() throws IOException {
        blackPieces = new ArrayList<Piece>();
        for(int i = 0; i < 8; i++){
            blackPieces.add(new Pawn(tiles[6][i], Color.BLACK, this));
        }
        blackPieces.add(new Rook(tiles[7][0], Color.BLACK, this));
        blackPieces.add(new Rook(tiles[7][7], Color.BLACK, this));

        blackPieces.add(new Bishop(tiles[7][2], Color.BLACK, this));
        blackPieces.add(new Bishop(tiles[7][5], Color.BLACK, this));

        blackPieces.add(new Knight(tiles[7][1], Color.BLACK, this));
        blackPieces.add(new Knight(tiles[7][6], Color.BLACK, this));

        blackPieces.add(new Queen(tiles[7][3], Color.BLACK, this));

        blackPieces.add(new King(tiles[7][4], Color.BLACK, this));

        whitePieces = new ArrayList<Piece>();
        for(int i = 0; i < 8; i++){
            whitePieces.add(new Pawn(tiles[1][i], Color.WHITE, this));
        }
        whitePieces.add(new Rook(tiles[0][0], Color.WHITE, this));
        whitePieces.add(new Rook(tiles[0][7], Color.WHITE, this));

        whitePieces.add(new Bishop(tiles[0][2], Color.WHITE, this));
        whitePieces.add(new Bishop(tiles[0][5], Color.WHITE, this));

        whitePieces.add(new Knight(tiles[0][1], Color.WHITE, this));
        whitePieces.add(new Knight(tiles[0][6], Color.WHITE, this));

        whitePieces.add(new Queen(tiles[0][3], Color.WHITE, this));

        whitePieces.add(new King(tiles[0][4], Color.WHITE, this));

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].visualise();
            }
        }

    }

    public void visualise() throws IOException {
        JFrame jFrame=new JFrame();
        JButton[][] chessBoardSquares = new JButton[8][8];
        JPanel chessBoard = new JPanel(new GridLayout(0, 8));
        chessBoard.setBorder(new LineBorder(Color.BLACK));


        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();

                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

                b.setIcon(icon);
                tiles[7 - ii][jj].setButton(b);
//                System.out.println("x == " + Integer.toString(7 - ii) + " y == " + Integer.toString(7 - jj));
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
//                        Image img = ImageIO.read(new File("./img/black-rook.png"));
//                        img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//                        b.setIcon(new ImageIcon(img));


                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                }
                chessBoardSquares[jj][ii] = b;
                final int jjj = jj;
                final int iii = 7 - ii;

                b.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
//                        System.out.println("Button " + Integer.toString(jjj) +" "+ Integer.toString(iii) + " pressed! "  + tiles[jjj][iii].x + " "+  tiles[jjj][iii].y);
                        try {
                            tileClickedAction(tiles[iii][jjj]);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
            }
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                chessBoard.add(chessBoardSquares[jj][ii]);

            }
        }
        chessBoard.add(new JLabel(""));

        jFrame.setSize(1000,1000);
        jFrame.add(chessBoard);
        jFrame.setVisible(true);
    }
    private void tileClickedAction(Tile tile) throws IOException {

        if(tileClicked != null){
//            System.out.println("Tile was clicked and it was not null " + tile.piece);
            if(tileClicked.piece != null && tileClicked.piece.color == turnOrder){
//                System.out.println("tile.piece == null && tileClicked.piece != null");
                if(tileClicked.piece.canMoveTo(tile)){
//                    if(tile.piece != null) {
//                        System.out.println("Wchodzi tutaj. " + tile.piece.color + tileClicked.piece.color);
//                    }
                    if(tile.piece == null){
                        tileClicked.piece.move(tile);
                        tileClicked.visualise();
                        tile.visualise();
                        if(turnOrder == Color.WHITE){
                            turnOrder = Color.BLACK;
                        } else {turnOrder = Color.WHITE;}
                    }
                    else if(tile.piece.color != tileClicked.piece.color){
                        deletePieceFromTable(tile.piece);
                        tile.piece = null;
                        tileClicked.piece.move(tile);
                        tileClicked.visualise();
                        tile.visualise();
                        if(turnOrder == Color.WHITE){
                            turnOrder = Color.BLACK;
                        } else {turnOrder = Color.WHITE;}
                    }

                }
            }
        }
        tileClicked = tile;
    }
    public boolean isSomethingBetween(Tile tile1, Tile tile2){
        if(tile1.isInLine(tile2)){
            if(tile1.x == tile2.x){
                for(int i = Math.min(tile1.y, tile2.y)+1; i < Math.max(tile1.y, tile2.y); i++){
                    if(tiles[i][tile1.x].piece != null){
                        return true;
                    }
                }
            }
            if(tile1.y == tile2.y){
                for(int i = Math.min(tile1.x, tile2.x)+1; i < Math.max(tile1.x, tile2.x); i++){
                    if(tiles[tile1.y][i].piece != null){
                        return true;
                    }
                }
            }
        }
        if(tile1.isDiagonal(tile2)){
            int xInc = Math.abs(tile1.x-tile2.x)/(tile1.x-tile2.x);
            int yInc = Math.abs(tile1.y-tile2.y)/(tile1.y-tile2.y);
            int xTemp = tile2.x + xInc;
            int yTemp = tile2.y + yInc;
//            System.out.println("xInc "+xInc+" xInc "+xInc);
//            System.out.println("tile1.x "+tile1.x+" tile1.y "+tile1.y);
//            System.out.println("tile2.x "+tile2.x+" tile2.y "+tile2.y);

            while(xTemp != tile1.x && yTemp != tile1.y){
                if(tiles[yTemp][xTemp].piece != null){
                    return true;
                }
                xTemp += xInc;
                yTemp += yInc;
            }
        }
        return false;
    }
    void deletePieceFromTable(Piece piece){
        if(piece.color == Color.WHITE){
            whitePieces.remove(piece);
        }
        else if(piece.color == Color.BLACK){
            blackPieces.remove(piece);
        }
    }
    public boolean tileIsAttacked(Tile tile, Color color){
        ArrayList<Piece> pieces = color == Color.WHITE ? whitePieces : blackPieces;

        for(Piece piece : pieces){
            if(piece.attacks(tile)){
                return true;
            }
        }
        return false;
    }
}
