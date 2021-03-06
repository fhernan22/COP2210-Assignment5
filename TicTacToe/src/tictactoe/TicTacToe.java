//********************************************************************************
// PANTHERID:  [6045594]
// CLASS: COP 2210 – [Fall 2016]
// ASSIGNMENT # [5]
// DATE: [11/27/2016]
//
// I hereby swear and affirm that this work is solely my own, and not the work 
// or the derivative of the work of someone else, except as outlined in the 
// assignment instructions.
//********************************************************************************
package tictactoe;

import javax.swing.JOptionPane;

/**
 *Creates a tic tac toe game
 * 
 * @author Fidel
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String name;
       Board board;
       int numberOfPlays = 0;
       int choice;
       int[] scoreCounter = new int[2];
       
       do
       {
           name = JOptionPane.showInputDialog("Please enter your name");
       }
       while (name.isEmpty());
       
       JOptionPane.showMessageDialog(null, "Hello " + name + ". Welcome to COP 2210 Tic Tac Toe!");
       
       // Game loop
        while (true)
        {
            board = new Board(name);
            board.initBoard();
            board.showScoreBoard(scoreCounter);
            JOptionPane.showMessageDialog(board, board.firstPlayer());
            
            while(!board.gameHasWinner() && numberOfPlays < 9)
            {   

                if (board.isComputerTurn())
                {
                    board.computerMove();
                    board.playerMove(false);
                }

                else
                {
                    while (!board.playerMadeMove())
                    {
                        board.initBoard();
                    }

                }
                numberOfPlays++; 
            }
            
            board.updateScoreBoard(scoreCounter);
            if (board.getWinner() == null)
                JOptionPane.showMessageDialog(board, "It's a tie!");
            else
                JOptionPane.showMessageDialog(board, board.getWinner().getPlayerName() + " wins!");
            
            Object[] options = {"yes", "no"};
            
            choice = JOptionPane.showOptionDialog(null,
                            "Would you like to play again?",
                            "",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
            
            if (choice == 0)
            {
                numberOfPlays = 0;
                board.cleanBoard();
                board.dispose();
            }
            else
            {
                board.showScoreBoard(scoreCounter);
                JOptionPane.showMessageDialog(board, "Thank you for playing " + name + "!");
                break;
            }
        }              
    }
    
}
