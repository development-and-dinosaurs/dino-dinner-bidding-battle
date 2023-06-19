package uk.co.developmentanddinosaurs.games.dinner.carnivore

import uk.co.developmentanddinosaurs.games.dinner.CarnivoreColour
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreHat
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore
import kotlin.properties.Delegates

/**
 * Test implementation of a [CraftyCodeCarnivore]
 */
class FairCarnivore : CraftyCodeCarnivore {
    private var playersRemaining by Delegates.notNull<Int>()
    private var meatRemaining by Delegates.notNull<Int>()

    override fun initialise(numberOfPlayers: Int, sizeOfMeat: Int) {
        playersRemaining = numberOfPlayers
        meatRemaining = sizeOfMeat
    }

    override fun update(meatEaten: Int) {
        playersRemaining -= 1
        meatRemaining -= meatEaten
    }

    override fun bid(): Int = meatRemaining / playersRemaining

    override fun colour(): CarnivoreColour = CarnivoreColour.ORANGE

    override fun hat(): CarnivoreHat = CarnivoreHat.TOP_HAT
}
