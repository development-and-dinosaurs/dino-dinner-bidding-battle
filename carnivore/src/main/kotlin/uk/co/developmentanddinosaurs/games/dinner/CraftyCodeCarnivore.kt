package uk.co.developmentanddinosaurs.games.dinner

interface CraftyCodeCarnivore {
    fun initialise(numberOfPlayers: Int, sizeOfMeat: Int)
    fun update(meatEaten: Int)
    fun bid(): Int
}
