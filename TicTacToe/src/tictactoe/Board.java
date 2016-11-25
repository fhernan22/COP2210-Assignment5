/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Fidel
 */
public class Board extends javax.swing.JFrame {   
    private Player player1;
    private Player player2;
    private String[][] gameBoard = new String[3][3];
    private boolean isComputerFirst = false;
    private boolean isComputer = false;
    private boolean hasPlayerMoved = false;
    private boolean gameHasWinner = false;
    private int currentPosition;
    private String computerAvatar;
    private int[][] winningCombinations = {
                                                {1,2,3},
                                                {1,4,7},
                                                {1,5,9},
                                                {2,5,8},
                                                {3,6,9},
                                                {3,5,7},
                                                {4,5,6},
                                                {7,8,9}
                                            };
    
    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
    }
    
    /**
     * Initializes an empty board centered in the middle
     * of the window
     */
    public void initBoard()
    {
        // Center the board
        setLocationRelativeTo(null);
        // Make board visible
        setVisible(true);
        
        gameBoard[0][0] = jButton1.getText();
        gameBoard[0][1] = jButton2.getText();
        gameBoard[0][2] = jButton3.getText();
        gameBoard[1][0] = jButton4.getText();
        gameBoard[1][1] = jButton5.getText();
        gameBoard[1][2] = jButton6.getText();
        gameBoard[2][0] = jButton7.getText();
        gameBoard[2][1] = jButton8.getText();
        gameBoard[2][2] = jButton9.getText();
    }
    
    /**
     * Empty every position in the board
     */
    public void cleanBoard()
    {
        jButton1.setText("");
        jButton2.setText("");
        jButton3.setText("");
        jButton4.setText("");
        jButton5.setText("");
        jButton6.setText("");
        jButton7.setText("");
        jButton8.setText("");
        jButton9.setText("");
    }
    
    /**
     *Determines who goes first the player or the computer
     * 
     * This method creates a random number. If the random 
     * number is less or equal to 5 the user goes first else 
     * the computer goes first
     * @param name Player's name
     */
    public void startingPlayer(String name)
    {
        Random r = new Random();
        int outcome = r.nextInt(10) + 1;
        
        if (outcome <= 5)
        {
            player1 = new Player(name);
            player1.setPlayerAvatar("X");
            
            player2 = new Player("Computer");
            player2.setPlayerAvatar("O");
        }
        else
        {
            player1 = new Player("Computer");
            player1.setPlayerAvatar("X");
            
            player2 = new Player(name);
            player2.setPlayerAvatar("O");
            
            this.isComputerFirst = true;
            this.isComputer = true;
        }
    }
    
    /**
     * 
     * @return who is the first player
     */
    public String firstPlayer()
    {
        return player1.getPlayerName() + " goes first!";
    }
    
    /**
     * 
     * @return if the computer is up next
     */
    public boolean isComputerTurn()
    {
        return this.isComputer;
        
    }
    
    /**
     * Switch turn between player and computer
     */
    public void switchTurn()
    {
        if (this.isComputer == true)
            this.isComputer = false;
        else
            this.isComputer = true;
    }
    
    public void playerMove(boolean move)
    {
        this.hasPlayerMoved = move;
    }
    
    public boolean playerMadeMove()
    {
        return this.hasPlayerMoved;
    }
    
    public void computerMove()
    {
        Random r = new Random();
        int position;
 
        position = r.nextInt(9) + 1;

        while (!convertToArray(position).equals(""))
        {  
            position = r.nextInt(9) + 1;   
        }
        
        if (this.isComputerFirst)
        {
            updateSpot(position, player1.getPlayerAvatar());
            this.computerAvatar = player1.getPlayerAvatar();
            addToCount(position, player1.getPointsCount());
        }   
        else
        {
            updateSpot(position, player2.getPlayerAvatar());
            this.computerAvatar = player2.getPlayerAvatar();
            addToCount(position, player2.getPointsCount());
        }

        this.currentPosition = position;
    }
    
    public String convertToArray(int position)
    {
        switch (position)
        {
            case 1:
                return gameBoard[0][0];
            case 2:
                return gameBoard[0][1];
            case 3:
                return gameBoard[0][2];
            case 4:
                return gameBoard[1][0];
            case 5:
                return gameBoard[1][1];
            case 6:
                return gameBoard[1][2];
            case 7:
                return gameBoard[2][0];
            case 8:
                return gameBoard[2][1];
            default:
                return gameBoard[2][2];
        }
    }
    
    public void updateSpot(int position, String avatar)
    {
        switch (position)
        {
            case 1:
                gameBoard[0][0] = avatar;
                break;
            case 2:
                gameBoard[0][1] = avatar;
                break;
            case 3:
                gameBoard[0][2] = avatar;
                break;
            case 4:
                gameBoard[1][0] = avatar;
                break;
            case 5:
                gameBoard[1][1] = avatar;
                break;
            case 6:
                gameBoard[1][2] = avatar;
                break;
            case 7:
                gameBoard[2][0] = avatar;
                break;
            case 8:
                gameBoard[2][1] = avatar;
                break;
            default:
                gameBoard[2][2] = avatar;
                break;
        }
    }
    
    public void addMoveToBoard()
    {
         switch (this.currentPosition)
         {
            case 1:
                jButton1.setText(this.computerAvatar);
                break;
            case 2:
                jButton2.setText(this.computerAvatar);
                break;
            case 3:
                jButton3.setText(this.computerAvatar);
                break;
            case 4:
                jButton4.setText(this.computerAvatar);
                break;
            case 5:
                jButton5.setText(this.computerAvatar);
                break;
            case 6:
                jButton6.setText(this.computerAvatar);
                break;
            case 7:
                jButton7.setText(this.computerAvatar);
                break;
            case 8:
                jButton8.setText(this.computerAvatar);
                break;
            default:
                jButton9.setText(this.computerAvatar);
                break;
            
         }
    }
    
    public void addToCount(int position, int[] pointsCount)
    {
        for (int i=0; i<winningCombinations.length; i++)
        {
            for (int j=0; j<winningCombinations[i].length; j++)
            {
                if (winningCombinations[i][j] == position)
                    pointsCount[i]++;
            }
        }
    }
    
    public void printBoard()
    {
        for (int i=0; i<gameBoard.length; i++)
        {
            for (int j=0; j<gameBoard[i].length; j++)
            {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
        
    }
    
    public boolean playerWon(int[] pointsCount)
    {
        for (int i=0; i<pointsCount.length; i++)
        {
            if (pointsCount[i] == 3)
                return true;
        }
        
        return false;
    }
    
    public boolean gameHasWinner()
    {
        return playerWon(player1.getPointsCount()) || playerWon(player2.getPointsCount());
    }
    
    /**
     * 
     * @param scoreCounter an array of two elements.
     * One to store a count of the player's total victories,
     * and the other to store a count of the computer total victories
     * 
     * @return  which player won the match or if there was a tie
     */
    public String winnerInfo()
    {
        if (this.isComputerFirst)
        {
            if (playerWon(player1.getPointsCount()))
            {
                return player1.getPlayerName() + " won!";
            }
            else if (playerWon(player2.getPointsCount()))
            {
                return player2.getPlayerName() + " won!";
            }
            else
                return "It's a tie";
        }
        else
        {
            if (playerWon(player2.getPointsCount()))
            {
                return player2.getPlayerName() + " won!";
            }
            else if (playerWon(player1.getPointsCount()))
            {
                return player1.getPlayerName() + " won!";
            }
            else
                return "It's a tie!";
        }
    }
    
    public void showScoreBoard(int[] scoreCount)
    {
        if (this.isComputerFirst)
        {
            scoreBoardLabel.setText(player2.getPlayerName() +
                                    "'s score: " + scoreCount[1] + " " +
                                    "                                        " +
                                    player1.getPlayerName() +
                                    "'s score: " + scoreCount[0]);
        }
        else
            scoreBoardLabel.setText(player1.getPlayerName() +
                                    "'s score: " + scoreCount[1] + " " +
                                    "                                        " +
                                    player2.getPlayerName() +
                                    "'s score: " + scoreCount[0]);
    }
    
    public void updateScoreBoard(int[] scoreCount)
    {
        if (this.isComputerFirst)
        {
            if (playerWon(player1.getPointsCount()))
            {
                scoreCount[0]++;
            }
            else if (playerWon(player2.getPointsCount()))
            {
                scoreCount[1]++;
            }
        }
        else
        {
            if (playerWon(player2.getPointsCount()))
            {
                scoreCount[0]++;
            }
            else if (playerWon(player1.getPointsCount()))
            {
                scoreCount[1]++;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scoreBoardLabel = new javax.swing.JLabel();
        Grid_Skeleton = new javax.swing.JPanel();
        Position1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Position2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Position3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        Position4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        Position5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        Position6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        Position7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        Position8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        Position9 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setPreferredSize(new java.awt.Dimension(600, 600));

        scoreBoardLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        scoreBoardLabel.setPreferredSize(new java.awt.Dimension(71, 40));
        getContentPane().add(scoreBoardLabel, java.awt.BorderLayout.PAGE_END);

        Grid_Skeleton.setBackground(new java.awt.Color(204, 204, 204));
        Grid_Skeleton.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        Position1.setBackground(new java.awt.Color(255, 255, 255));
        Position1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position1.setLayout(new java.awt.BorderLayout());

        jButton1.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 153, 255));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Position1.add(jButton1, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position1);

        Position2.setBackground(new java.awt.Color(255, 255, 255));
        Position2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position2.setLayout(new java.awt.BorderLayout());

        jButton2.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Position2.add(jButton2, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position2);

        Position3.setBackground(new java.awt.Color(255, 255, 255));
        Position3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position3.setLayout(new java.awt.BorderLayout());

        jButton3.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 153, 255));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        Position3.add(jButton3, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position3);

        Position4.setBackground(new java.awt.Color(255, 255, 255));
        Position4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position4.setLayout(new java.awt.BorderLayout());

        jButton4.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 255));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        Position4.add(jButton4, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position4);

        Position5.setBackground(new java.awt.Color(255, 255, 255));
        Position5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position5.setLayout(new java.awt.BorderLayout());

        jButton5.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 153, 255));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Position5.add(jButton5, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position5);

        Position6.setBackground(new java.awt.Color(255, 255, 255));
        Position6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position6.setLayout(new java.awt.BorderLayout());

        jButton6.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 153, 255));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        Position6.add(jButton6, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position6);

        Position7.setBackground(new java.awt.Color(255, 255, 255));
        Position7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position7.setLayout(new java.awt.BorderLayout());

        jButton7.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 153, 255));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        Position7.add(jButton7, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position7);

        Position8.setBackground(new java.awt.Color(255, 255, 255));
        Position8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position8.setLayout(new java.awt.BorderLayout());

        jButton8.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 153, 255));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        Position8.add(jButton8, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position8);

        Position9.setBackground(new java.awt.Color(255, 255, 255));
        Position9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Position9.setLayout(new java.awt.BorderLayout());

        jButton9.setFont(new java.awt.Font("Arial", 1, 100)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 153, 255));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        Position9.add(jButton9, java.awt.BorderLayout.CENTER);

        Grid_Skeleton.add(Position9);

        getContentPane().add(Grid_Skeleton, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (gameBoard[0][0].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton1.setText(player2.getPlayerAvatar());
                addToCount(1, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton1.setText(player1.getPlayerAvatar());
                addToCount(1, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
   
        gameBoard[0][0] = jButton1.getText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        if (gameBoard[0][1].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton2.setText(player2.getPlayerAvatar());
                addToCount(2, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton2.setText(player1.getPlayerAvatar());
                addToCount(2, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }

        gameBoard[0][1] = jButton2.getText();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (gameBoard[0][2].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton3.setText(player2.getPlayerAvatar());
                addToCount(3, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton3.setText(player1.getPlayerAvatar());
                addToCount(3, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[0][2] = jButton3.getText();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (gameBoard[1][0].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton4.setText(player2.getPlayerAvatar());
                addToCount(4, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton4.setText(player1.getPlayerAvatar());
                addToCount(4, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[1][0] = jButton4.getText();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (gameBoard[1][1].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton5.setText(player2.getPlayerAvatar());
                addToCount(5, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton5.setText(player1.getPlayerAvatar());
                addToCount(5, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[1][1] = jButton5.getText();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (gameBoard[1][2].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton6.setText(player2.getPlayerAvatar());
                addToCount(6, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton6.setText(player1.getPlayerAvatar());
                addToCount(6, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }

        gameBoard[1][2] = jButton6.getText();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        if (gameBoard[2][0].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton7.setText(player2.getPlayerAvatar());
                addToCount(7, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton7.setText(player1.getPlayerAvatar());
                addToCount(7, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[2][0] = jButton7.getText();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       
        if (gameBoard[2][1].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton8.setText(player2.getPlayerAvatar());
                addToCount(8, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton8.setText(player1.getPlayerAvatar());
                addToCount(8, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[2][1] = jButton8.getText();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if (gameBoard[2][2].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton9.setText(player2.getPlayerAvatar());
                addToCount(9, player2.getPointsCount());
                playerWon(player2.getPointsCount());
            }
            else
            {
                jButton9.setText(player1.getPlayerAvatar());
                addToCount(9, player1.getPointsCount());
                playerWon(player1.getPointsCount());
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[2][2] = jButton9.getText();
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */   
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Board().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Grid_Skeleton;
    private javax.swing.JPanel Position1;
    private javax.swing.JPanel Position2;
    private javax.swing.JPanel Position3;
    private javax.swing.JPanel Position4;
    private javax.swing.JPanel Position5;
    private javax.swing.JPanel Position6;
    private javax.swing.JPanel Position7;
    private javax.swing.JPanel Position8;
    private javax.swing.JPanel Position9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel scoreBoardLabel;
    // End of variables declaration//GEN-END:variables
}
