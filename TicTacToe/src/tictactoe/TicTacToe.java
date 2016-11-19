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
       
       do
       {
           name = JOptionPane.showInputDialog("Please enter your name");
       }
       while (name.isEmpty());
       
       JOptionPane.showMessageDialog(null, "Hello " + name + ". Welcome to COP 2210 Tic Tac Toe!");
       
        board = new Board();
        // Center the board
        board.setLocationRelativeTo(null);
        // Make board visible
        board.setVisible(true);
    }
    
}
