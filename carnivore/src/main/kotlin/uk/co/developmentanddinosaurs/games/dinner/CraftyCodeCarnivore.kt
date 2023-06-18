package uk.co.developmentanddinosaurs.games.dinner

/**
 * The interface for the bot player in Dino Dinner Democracy.
 *
 * This interface provides the functions required to take part in Dino Dinner Democracy. These functions must be
 * implemented to take part in the game.
 */
interface CraftyCodeCarnivore {
    /**
     * Initialises the CraftyCodeCarnivore at the start of the game.
     *
     * The initialise function is called at the start of the game after constructing your instance, but before
     * progressing through any rounds.
     *
     * You should perform any initialisation required for the game in this method instead of the constructor, for any
     * initialisation that requires knowledge of the game in progress. If you are writing files or initialising random,
     * you can still do this in an init block as required.
     *
     * This function will always be called before any other function, so you can safely use `lateinit` or NonNull
     * delegates for anything that relies on the information provided by this function call.
     *
     * @param numberOfPlayers the number of players in this game
     * @param sizeOfMeat the size of the meat in this game
     */
    fun initialise(numberOfPlayers: Int, sizeOfMeat: Int)

    /**
     * Updates the state of the game each round.
     *
     * The update function is called once per round at the start of the round. This function serves to inform your
     * [CraftyCodeCarnivore] of the amount of meat that was eaten last round.
     *
     * You can assume that each time this function is called, the number of players remaining in the game is one less
     * than it was the last time this function was called.
     *
     * The update function will not be called if your `Carnivore` was successful in the last bid. You are too busy
     * eating your own meat to pay attention to the other `Carnivore`s in the game.
     *
     * @param meatEaten the amount of meat eaten last round
     */
    fun update(meatEaten: Int)

    /**
     * Collects a bid for your share of the meat.
     *
     * The bid function is your main action for the game, where you return an integer value for the amount of meat you
     * want to bid for.
     *
     * There are no restrictions placed on this function. It would be smart to not bid for more meat than there is left,
     * as you'll always lose if your bid can't be fulfilled, but this isn't enforced directly.
     *
     * @return the amount of meat you want to bid this round
     */
    fun bid(): Int

    /**
     * The colour of your carnivore.
     *
     * Pick the [CarnivoreColour] that will be used to colour your player in the game.
     *
     * @return the colour of your carnivore
     */
    fun colour(): CarnivoreColour

    /**
     * The hat for your carnivore.
     *
     * Pick the [CarnivoreHat] that your CraftyCodeCarnivore will wear in the game.
     *
     * @return the hat for your carnivore
     */
    fun hat(): CarnivoreHat
}
