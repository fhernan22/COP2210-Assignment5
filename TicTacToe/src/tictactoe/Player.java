
package tictactoe;

/**
 *Initializes a player
 * 
 * 
 * @author Fidel
 */
public class Player {
    private String playerName;
    private String playerAvatar;
    private int numVictories = 0;
    private int[] pointsCount = new int[8];
    private boolean isWinner = false;
    
    /**
     * Construct and initializes a player's name
     * and the number of coins they have
     * 
     * @param name Player's name
     */
    public Player(String name)
    {
        this.playerName = name;
    }
    
    /**
     * Set the name of the player
     * 
     * @param name Player's name
     */
    public void setPlayerName(String name)
    {
        this.playerName = name;
    }
    
    /**
     * Set the player's avatar
     * @param playerAvatar Player's avatar
     */
    public void setPlayerAvatar(String playerAvatar)
    {
        this.playerAvatar = playerAvatar;
    }
    
    /**
     * Add one victory to a given player
     */
    public void winner()
    {
        this.numVictories++;
    }
    
    /**
     * 
     * @return all matches won by a given player
     */
    public int getTotalVictories()
    {
        return this.numVictories;
    }
    
    public boolean hasPlayerWon()
    {
        return this.isWinner;
    }
    
    /**
     * Get the player's name
     * 
     * @return The player's name
     */
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    /**
     * Get the player's avatar
     * 
     * @returnPlayer's avatar 
     */
    public String getPlayerAvatar()
    {
        return this.playerAvatar;
    }
    
    public int[] getPointsCount()
    {
        return this.pointsCount;
    }
    
    public void printCount()
    {
        for (int i=0; i<this.pointsCount.length; i++)
            System.out.print(this.pointsCount[i] + " ");
        System.out.println();
    }
}
