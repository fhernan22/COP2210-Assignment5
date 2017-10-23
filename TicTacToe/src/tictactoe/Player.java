
package tictactoe;

/**
 *Initializes a player along with his characteristics
 * 
 * 
 * @author Fidel
 */
public class Player {
    private String playerName;
    private String playerAvatar;
    private boolean isAI = false;
    
    /**
     * Construct and initializes a player's name
     * and the number of coins they have
     * 
     * @param name Player's name
     */
    public Player(String name, boolean isAI)
    {
        this.playerName = name;
        this.isAI = isAI;
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
    
    public void AIPlayer(boolean result)
    {
        this.isAI = result;
    }
    
    public boolean iSPLayerAI()
    {
        return this.isAI;
    }
}
