package uk.co.developmentanddinosaurs.games.dinner.logic

import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Game coordinator
 *
 * [MummyTrex] is the overseer of the game and is the main logic driver behind the game implementation.
 */
class MummyTrex {
    private val amountOfMeat = Random.nextInt(1000..2000)

    /**
     * Returns the amount of meat that is left to be bid on
     *
     * @return the amount of meat remaining to be bid on
     */
    fun bringHomeTheBacon(): Int = amountOfMeat
}