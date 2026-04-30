package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    public static final String PICTURE_PATH = "/workspaces/chess2026/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = PICTURE_PATH + "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = PICTURE_PATH + "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = PICTURE_PATH + "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = PICTURE_PATH + "bknight.png";
    private static final String RESOURCES_WROOK_PNG = PICTURE_PATH + "wrook.png";
    private static final String RESOURCES_BROOK_PNG = PICTURE_PATH + "brook.png";
    private static final String RESOURCES_WKING_PNG = PICTURE_PATH + "wking.png";
    private static final String RESOURCES_BKING_PNG = PICTURE_PATH + "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = PICTURE_PATH + "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = PICTURE_PATH + "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = PICTURE_PATH + "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = PICTURE_PATH + "bpawn.png";

    //constant used to keep track of where the piece should be drawn when the user is dragging it
    private static final int PIECE_OFFSET = 24;

    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    //the square your piece came from when the user tries to move it.
    private Square fromMoveSquare;
    //the square your piece tries to go to when the user tries to move it.
    private Square endSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from white to black.
        //IMPORTANT**** : please note for each square you create you HAVE to do "this.add(<your new square here>)" 
        //the reason this is required has to do with how visual components are rendered, so if you neglect to do this
        //you will not see any of your squares show up on the board!
        // Where's the "add" method? Stay tuned for next unit where we discover where it is and why we can do this action.
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (i%2 !=0){
                if(j%2 != 0){
                board[i][j] = new Square(this, true, i, j);
                this.add(board[i][j]);
                }
                else{
                    board[i][j] = new Square(this, false, i, j);
                    this.add(board[i][j]);
                }
                }
                else{
                    if(j%2 != 0){
                board[i][j] = new Square(this, false, i, j);
                this.add(board[i][j]);
                }
                else{
                    board[i][j] = new Square(this, true, i, j);
                    this.add(board[i][j]);
                }
                }
            }
        }
        
        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    void initializePieces() {

    	 board[2][7].put(new Bubble(true, RESOURCES_WKING_PNG));
         board[5][0].put(new Bubble(false, RESOURCES_BKING_PNG));
         board[7][3].put(new King(false, RESOURCES_BKING_PNG));
         board[0][3].put(new King(true, RESOURCES_WKING_PNG));
         board[0][4].put(new Queen(true, RESOURCES_WQUEEN_PNG));
         board[7][4].put(new Queen(false, RESOURCES_BQUEEN_PNG));
         board[0][0].put(new Rook(true, RESOURCES_WROOK_PNG));
         board[0][7].put(new Rook(true, RESOURCES_WROOK_PNG));
         board[7][0].put(new Rook(false, RESOURCES_BROOK_PNG));
         board[7][7].put(new Rook(false, RESOURCES_BROOK_PNG));
         board[0][1].put(new Knight(true, RESOURCES_WKNIGHT_PNG));
         board[0][6].put(new Knight(true, RESOURCES_WKNIGHT_PNG));
         board[7][1].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
         board[7][6].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
         board[0][2].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
         board[0][5].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
         board[7][2].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
         board[7][5].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
         for(int i = 1; i < 7; i++){
            board[6][i].put(new Pawn(false, RESOURCES_BPAWN_PNG));
         }
          for(int i = 1; i < 7; i++){
           board[1][i].put(new Pawn(true, RESOURCES_WPAWN_PNG));
         }

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].draw(g);
            }
        }

        if (currPiece != null) {
            if (currPiece.getColor() == whiteTurn) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == whiteTurn) {
            currPiece = sq.getOccupyingPiece();
            //   for(Square s: currPiece.getControlledSquares(board, sq)){
            //     s.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
            // }
            System.out.println(currPiece.getLegalMoves(this, sq).size());
            for(Square s: currPiece.getLegalMoves(this, sq)){
                s.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
            }
            fromMoveSquare = sq;
            sq.setDisplay(false);
        }
        repaint();
    }
    public boolean isInCheck(boolean kingColor){
        // if (); 
        //loop through the whole board
        //ask each square do you have a piece? If they do ask the piece are you the opposite color of kingColor
        //ask each of those piece for the squares they control - arrayList
        //loop through the controlled squares and ask each of those squares "do you have a piece?" "is that piece a(instanceof) king" "is that king's color kingColor"
        //if all of that happens return true
         for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j].isOccupied() && board[i][j].getOccupyingPiece().getColor() != kingColor){
                    System.out.println(board[i][j].getOccupyingPiece());
                    ArrayList<Square> list = board[i][j].getOccupyingPiece().getControlledSquares(board,board[i][j]);
                    for (int a = 0; a < list.size(); a++){
                        if(list.get(a).isOccupied() && list.get(a).getOccupyingPiece() instanceof King && list.get(a).getOccupyingPiece().getColor() == kingColor){
                            return true;
                        }
                    }
                }

                }
        }
        return false;
    }
    
    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by moving the new piece to it's new board location.
    @Override
    public void mouseReleased(MouseEvent e) {
    	  for(Square[] row: board){
              for(Square s: row){
                  s.setBorder(null);
              }
          }
         Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
         if (currPiece != null){
           ArrayList<Square> canmovetoo = currPiece.getLegalMoves(this, fromMoveSquare);
          System.out.println("EndSquare:");
          System.out.println(endSquare);
          System.out.println(canmovetoo.size());
          for(int i = 0; i < canmovetoo.size(); i++){
               System.out.println(i);
               System.out.println(canmovetoo.get(i));
            if (endSquare == canmovetoo.get(i)){
              //make move
              
              System.out.println("inside");
                Piece captured = endSquare.getOccupyingPiece();
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
                if(isInCheck(whiteTurn)){
                  fromMoveSquare.put(currPiece);
                  endSquare.put(captured);
                }
                else{
                  whiteTurn = !whiteTurn;
                }
            }
          }
         }
         if (fromMoveSquare!= null)
    fromMoveSquare.setDisplay(true);

      currPiece = null;
      fromMoveSquare = null;

      repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - PIECE_OFFSET;
        currY = e.getY() - PIECE_OFFSET;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}