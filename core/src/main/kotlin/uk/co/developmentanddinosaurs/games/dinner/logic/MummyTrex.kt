package uk.co.developmentanddinosaurs.games.dinner.logic

import kotlin.random.Random
import kotlin.random.nextInt
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore

/**
 * Game coordinator
 *
 * [MummyTrex] is the overseer of the game and is the main logic driver behind the game
 * implementation.
 */
class MummyTrex {
    private var amountOfMeat = Random.nextInt(1000..2000)
    private var winningBid = 0

    /** The winning carnivore (so far) */
    lateinit var winner: CraftyCodeCarnivore

    /**
     * Returns the amount of meat that is left to be bid on.
     *
     * @return the amount of meat remaining to be bid on
     */
    fun bringHomeTheBacon(): Int = amountOfMeat

    /**
     * Updates the amount of meat that is left to be bid on.
     *
     * @param meatEaten the amount of meat that has been eaten this round
     */
    fun updateMeat(meatEaten: Int) {
        amountOfMeat -= meatEaten
    }

    /**
     * Evaluates the carnivore for this round.
     *
     * Called each round to evaluate the latest bid, which when all rounds have been completed will
     * allow us to crown the winner.
     *
     * @param codeCarnivore the carnivore that made the bid
     * @param bid the bid the carnivore made
     */
    fun evaluate(codeCarnivore: CraftyCodeCarnivore, bid: Int) {
        if (bid > winningBid) {
            winningBid = bid
            winner = codeCarnivore
        }
    }
}
