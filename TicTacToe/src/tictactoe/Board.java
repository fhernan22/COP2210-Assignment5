
package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Creates a tic tac toe game board
 * 
 * @author Fidel
 */
public class Board extends javax.swing.JFrame {   
    private Player player1;
    private Player player2;
    private String[] gameBoard = new String[9];
    private boolean isComputerFirst = false;
    private boolean isComputer = false;
    private boolean hasPlayerMoved = false;
    private boolean gameHasWinner = false;
    private int numPlays;
    
    /**
     *Determines who goes first the player or the computer
     * 
     * If the random 
     * number is less or equal to 5 the user goes first else 
     * the computer goes first
     * @param name Player's name
     */
    
    public Board(String name) {
        initComponents();
        Random r = new Random();
        int outcome = r.nextInt(10) + 1;
        this.numPlays = 0;
        
        if (outcome <= 5)
        {
            player1 = new Player(name, false);
            player1.setPlayerAvatar("X");
            
            player2 = new Player("Computer", true);
            player2.setPlayerAvatar("O");
        }
        else
        {
            player1 = new Player("Computer", true);
            player1.setPlayerAvatar("X");
            
            player2 = new Player(name, false);
            player2.setPlayerAvatar("O");
            
            this.isComputerFirst = true;
            this.isComputer = true;
        }
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
        
        this.numPlays = 0;
        gameBoard[0] = jButton1.getText();
        gameBoard[1] = jButton2.getText();
        gameBoard[2] = jButton3.getText();
        gameBoard[3]= jButton4.getText();
        gameBoard[4] = jButton5.getText();
        gameBoard[5] = jButton6.getText();
        gameBoard[6] = jButton7.getText();
        gameBoard[7] = jButton8.getText();
        gameBoard[8] = jButton9.getText();
    }
    
    /**
     * Empty every position in the board
     */
    public void cleanBoard()
    {
        this.numPlays = 0;
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
     * Display a message of who goes first
     * 
     * @return who is the first player
     */
    public String firstPlayer()
    {
        return player1.getPlayerName() + " goes first!";
    }
    
    /**
     * Check if the computer is next
     * 
     * @return if the computer is up next
     */
    public boolean isComputerTurn()
    {
        return this.isComputer;
        
    }
    
    /**
     * Updates if the player has moved
     * @param move true if the player moves false otherwise
     */
    public void playerMove(boolean move)
    {
        this.numPlays++;
        this.hasPlayerMoved = move;
    }
    
    /**
     * Checks whether the player moved or not
     * 
     * @return if the player has moved
     */
    public boolean playerMadeMove()
    {
        return this.hasPlayerMoved;
    }
    
    /**
     * Determine which spot the computer is going to move to.
     * 
     * Creates a random number that represents one of 9 positions in the board.
     * Then updates this position on the game board with the computer's avatar 
     * 
     */
    public void computerMove()
    {
        int index = -1;

        if (this.isComputerFirst)
        {

            index = minimax(this.gameBoard, true, player1)[1];
            this.gameBoard[index] = player1.getPlayerAvatar();
            addMoveToBoard(index, player1);
            this.isComputer = false;    
        }
            
        else
        {

            index = minimax(this.gameBoard, true, player2)[1];
            this.gameBoard[index] = player2.getPlayerAvatar();
            addMoveToBoard(index, player2);
            this.isComputer = false;
        }
            

        this.numPlays++;
    }
    
    /**
     * Checks if there is a winner
     * @return true if there is a winner false otherwise
     */
    public boolean gameHasWinner()
    {
        return playerWon(this.gameBoard, player1) || playerWon(this.gameBoard, player2);
    }
    
    /**
     * Checks and return which player won the match,
     * or if we have a tie
     * 
     * @return who won the match
     */
    public Player getWinner()
    {
        if (this.isComputerFirst)
        {
            if (playerWon(this.gameBoard, player1))
            {
                return player1;
            }
            else if (playerWon(this.gameBoard, player2))
            {
                return player2;
            }
            else
                return null;
        }
        else
        {
            if (playerWon(this.gameBoard, player2))
            {
                return player2;
            }
            else if (playerWon(this.gameBoard, player1))
            {
                return player1;
            }
            else
                return null;
        }
    }
    

    
    /**
     * Keeps track of the score board
     * 
     * @param scoreCount array of two elements. 
     * The first elements stores the computer's score, while
     * the second element stores the human player's score
     */
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
    
    /**
     * Updates the score board
     * 
     * @param scoreCount array of two elements.
     * The first elements stores the computer's score, while
     * the second element stores the human player's score
     */
    public void updateScoreBoard(int[] scoreCount)
    {
        if (this.isComputerFirst)
        {
            if (playerWon(this.gameBoard, player1))
            {
                scoreCount[0]++;
            }
            else if (playerWon(this.gameBoard, player2))
            {
                scoreCount[1]++;
            }
        }
        else
        {
            if (playerWon(this.gameBoard, player2))
            {
                scoreCount[0]++;
            }
            else if (playerWon(this.gameBoard, player1))
            {
                scoreCount[1]++;
            }
        }
    }
    
    /***********************************************************************
     * ************************Helper Fucntions****************************/
    
    private int[] minimax(String[] board, boolean maximizingPlayer, Player player)
    {
        ArrayList<Integer> actions = availableSpots(board);
        int bestIndex = 1;
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        int[] node = new int[2];
        
        if (playerWon(board, player) && player.iSPLayerAI())
        {
            node[0] = 10;
            return node;
        }
            
        else if (playerWon(board, player) && !player.iSPLayerAI())
        {
            node[0] = -10;
            return node;
        }
            
        else if (actions.size() == 0)
        {
            node[0] = 0;
            return node;
        }
        
        if (maximizingPlayer)
        {
            
            int val = Integer.MIN_VALUE;
            
            for (Integer action: actions)
            {
                String[] tempBoard = Arrays.copyOf(board, board.length);
                tempBoard[action] = player.getPlayerAvatar();

                if (this.isComputerFirst)
                    val = minimax(tempBoard, false, player2)[0];
                else
                    val = minimax(tempBoard, false, player1)[0];
                            
                if (val >= maxScore)
                {
                    maxScore = val;
                    bestIndex = action;
                }
            }
            
            node[0] = maxScore;
            node[1] = bestIndex;
        }
        else
        {
            
            int val = Integer.MAX_VALUE;
            
            for (Integer action: actions)
            {
                String[] tempBoard = Arrays.copyOf(board, board.length);
                tempBoard[action] = player.getPlayerAvatar();
                
                if (this.isComputerFirst)
                    val = minimax(tempBoard, true, player1)[0];
                else
                    val = minimax(tempBoard, true, player2)[0];
                                  
                if (val <= minScore)
                {
                    minScore = val;
                    bestIndex = action;
                }
            }
            
            node[0] = minScore;
            node[1] = bestIndex;
        }
        return node;
    }
    
    private ArrayList<Integer> availableSpots(String[] board)
    {
        ArrayList<Integer> spots = new ArrayList<>();
        
        for (int i=0; i<board.length; i++)
        {
            if (!board[i].equals("X") && !board[i].equals("O"))
                spots.add(i);
        }
        return spots;
    }
    
    /**
     * Updates a position in the board with a player's avatar
     * 
     * @param position position we want to update
     * @param avatar player's avatar 
     */
    private void updateSpot(int position, String avatar)
    {
        gameBoard[position - 1] = avatar;
    }
    
    /**
     * Check the computer's current position and adds the computer's avatar
     * to the appropriate button  
     */
    private void addMoveToBoard(int position, Player player)
    {
         switch (position)
         {
            case 0:
                jButton1.setText(player.getPlayerAvatar());
                break;
            case 1:
                jButton2.setText(player.getPlayerAvatar());
                break;
            case 2:
                jButton3.setText(player.getPlayerAvatar());
                break;
            case 3:
                jButton4.setText(player.getPlayerAvatar());
                break;
            case 4:
                jButton5.setText(player.getPlayerAvatar());
                break;
            case 5:
                jButton6.setText(player.getPlayerAvatar());
                break;
            case 6:
                jButton7.setText(player.getPlayerAvatar());
                break;
            case 7:
                jButton8.setText(player.getPlayerAvatar());
                break;
            default:
                jButton9.setText(player.getPlayerAvatar());
                break;
            
         }
    }
    
    /**
     * Return whether there is a winner or not
     * 
     * Check all possible combinations of winning moves.
     * If a particular combination is populated by a player's 
     * avatar there is a winner otherwise there is a tie.
     * 
     * @param avatar player's avatar
     * @return true if the player won the match, false otherwise
     */
    private boolean playerWon(String[] board, Player player)
    {
        if ((board[0].equals(player.getPlayerAvatar()) && board[1].equals(player.getPlayerAvatar()) &&  board[2].equals(player.getPlayerAvatar())) ||
            (board[3].equals(player.getPlayerAvatar()) && board[4].equals(player.getPlayerAvatar()) &&  board[5].equals(player.getPlayerAvatar())) ||
            (board[0].equals(player.getPlayerAvatar()) && board[3].equals(player.getPlayerAvatar()) &&  board[6].equals(player.getPlayerAvatar())) ||
            (board[6].equals(player.getPlayerAvatar()) && board[7].equals(player.getPlayerAvatar()) &&  board[8].equals(player.getPlayerAvatar())) ||
            (board[1].equals(player.getPlayerAvatar()) && board[4].equals(player.getPlayerAvatar()) &&  board[7].equals(player.getPlayerAvatar())) ||
            (board[2].equals(player.getPlayerAvatar()) && board[5].equals(player.getPlayerAvatar()) &&  board[8].equals(player.getPlayerAvatar())) ||
            (board[0].equals(player.getPlayerAvatar()) && board[4].equals(player.getPlayerAvatar()) &&  board[8].equals(player.getPlayerAvatar())) ||
            (board[2].equals(player.getPlayerAvatar()) && board[4].equals(player.getPlayerAvatar()) &&  board[6].equals(player.getPlayerAvatar())))
                return true;
        
        return false;
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
        
        if (gameBoard[0].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton1.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton1.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
   
        gameBoard[0] = jButton1.getText();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      
        if (gameBoard[1].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton2.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton2.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }

        gameBoard[1] = jButton2.getText();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (gameBoard[2].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton3.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton3.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[2] = jButton3.getText();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (gameBoard[3].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton4.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton4.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[3] = jButton4.getText();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (gameBoard[4].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton5.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton5.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[4] = jButton5.getText();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (gameBoard[5].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton6.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton6.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }

        gameBoard[5] = jButton6.getText();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        if (gameBoard[6].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton7.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton7.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[6] = jButton7.getText();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       
        if (gameBoard[7].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton8.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton8.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[7] = jButton8.getText();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        if (gameBoard[8].equals(""))
        {
            if (this.isComputerFirst)
            {
                jButton9.setText(player2.getPlayerAvatar());
                playerWon(this.gameBoard, player2);
            }
            else
            {
                jButton9.setText(player1.getPlayerAvatar());
                playerWon(this.gameBoard, player1);
            }
            
            this.hasPlayerMoved = true;
            this.isComputer = true;
        }
        else
        {
            this.hasPlayerMoved = false;
            this.isComputer = false;
        }
        
        gameBoard[8] = jButton9.getText();
    }//GEN-LAST:event_jButton9ActionPerformed


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
