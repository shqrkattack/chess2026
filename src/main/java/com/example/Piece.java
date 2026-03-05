package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//you will need to implement two functions in this file.
public class Piece {

private boolean color;   // true = white, false = black
    private Image img;

    public Piece(boolean color, String imagePath) {
        this.color = color;
        this.img = new ImageIcon(imagePath).getImage();
    }

    public boolean getColor() {
        return color;
    }

    public Image getImage() {
        return img;
    }

    public void draw(Graphics g, Square currentSquare) {
        g.drawImage(this.img, currentSquare.getX(), currentSquare.getY(), null);
    }    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
     return null;
    }
    public ArrayList<Square> getLegalMoves(Board b, Square start){

    ArrayList<Square> moves = new ArrayList<>();
    Square[][] board = b.getSquareArray();

    int row = start.getRow();
    int col = start.getCol();

    int[][] directions = {
        {1,0}, {-1,0}, {0,1}, {0,-1},   // vertical + horizontal
        {1,1}, {1,-1}, {-1,1}, {-1,-1}  // diagonals
    };

    for (int[] d : directions){

        int enemyRow = row + d[0];
        int enemyCol = col + d[1];

        int landRow = row + 2*d[0];
        int landCol = col + 2*d[1];

        if (enemyRow >=0 && enemyRow <8 && enemyCol >=0 && enemyCol <8 &&
            landRow >=0 && landRow <8 && landCol >=0 && landCol <8){

            Square enemySquare = board[enemyRow][enemyCol];
            Square landingSquare = board[landRow][landCol];

            if (enemySquare.isOccupied() &&
                enemySquare.getOccupyingPiece().getColor() != this.getColor() &&
                !landingSquare.isOccupied()){

                moves.add(landingSquare);
            }
        }
    }

    return moves;
}

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public boolean isLegalMove(Square[][] board, int fromRow, int fromCol, int toRow, int toCol) {
    Square start = board[fromRow][fromCol];
    Square end = board[toRow][toCol];

    // get the board object from the square
    Board b = start.getBoard();

    ArrayList<Square> legalMoves = getLegalMoves(b, start);

    if (legalMoves == null) {
        return false;
    }
    return legalMoves.contains(end);
    }
}