package uk.co.developmentanddinosaurs.games.dinner.carnivore

import uk.co.developmentanddinosaurs.games.dinner.CarnivoreColour
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreHat
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Test implementation of a [CraftyCodeCarnivore]
 */
class FairCarnivore : CraftyCodeCarnivore {
    private var playersRemaining by Delegates.notNull<Int>()
    private var meatRemaining by Delegates.notNull<Int>()
    private val random = Random.Default

    override fun initialise(numberOfPlayers: Int, sizeOfMeat: Int) {
        playersRemaining = numberOfPlayers
        meatRemaining = sizeOfMeat
    }

    override fun update(meatEaten: Int) {
        playersRemaining -= 1
        meatRemaining -= meatEaten
    }

    override fun bid(): Int =
        random.nextInt(meatRemaining / (playersRemaining + 1) until meatRemaining / (playersRemaining - 1))

    override fun colour(): CarnivoreColour = colours.removeFirst()

    override fun hat(): CarnivoreHat = hats.removeFirst()

    companion object {
        private val colours = CarnivoreColour.values().apply { shuffle() }.toMutableList()
        private val hats = CarnivoreHat.values().apply { shuffle() }.toMutableList()
    }
}
