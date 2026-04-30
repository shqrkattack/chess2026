package com.example;
import java.util.ArrayList;

//you will need to implement two functions in this file.
public class Bubble extends Piece {

    public Bubble(boolean color, String imagePath) {
        super(color, imagePath);
      
    }


    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        //can capture only the ones in fornt
        int row = start.getRow();
        int col = start.getCol();
        ArrayList<Square> controlledSquares = new ArrayList<Square>();
       
        //white piece logic
        if(color){
            if(row+1<= 7 && (!board[row+1][col].isOccupied())){
                moves.add(board[row+1][col]);
        }
    }
    //black piece logic
    else{
        if(row>= 0 && (!board[row-1][col].isOccupied())){
            moves.add(board[row-1][col]);
        }
    }
    return moves;
    }
    public ArrayList<Square> getLegalMoves(Board b, Square start){

    ArrayList<Square> moves = new ArrayList<>();
    Square[][] board = b.getSquareArray();

    int row = start.getRow();
    int col = start.getCol();
    //white piece logic
    if(color){
        if(row+1<= 7 && (!board[row+1][col].isOccupied()||board[row+1][col].getOccupyingPiece().getColor()!= color)){
            moves.add(board[row+1][col]);
        }
    }
    //black piece logic
    else{
        if(row>= 0 && (!board[row-1][col].isOccupied()||board[row-1][col].getOccupyingPiece().getColor()!= color)){
            moves.add(board[row-1][col]);
        }
    }

    return moves;
}


}