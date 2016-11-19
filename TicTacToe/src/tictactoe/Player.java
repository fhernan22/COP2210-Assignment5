
package tictactoe;

/**
 *Initializes a player
 * 
 * 
 * @author Fidel
 */
public class Player {
    private String playerName;
    private int playerCoins;
    
    /**
     * Construct and initializes a player's name
     * and the number of coins they have
     * 
     * @param name Player's name
     */
    public Player(String name)
    {
        this.playerName = name;
        this.playerCoins = 0;
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
     * Add coins to the player's account
     * 
     * @param coins Coins to be added 
     */
    public void setPlayerCoin(int coins)
    {
        this.playerCoins = coins;
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
     * Get the player's total amount of coins
     * 
     * @return Total amount of coins
     */
    public int getTotalCoins()
    {
        return this.playerCoins;
    }
}
