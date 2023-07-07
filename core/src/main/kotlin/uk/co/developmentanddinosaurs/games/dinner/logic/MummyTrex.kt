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
   * Resets Mummy T-Rex so she can play the game again.
   *
   * Resets the amount of meat and the winning bid to be the initial values. Does not do anything
   * with the current winning carnivore, for two reasons. First, there isn't a good default value
   * other than null, which I'd rather not make it nullable. Second, it doesn't really matter as the
   * first round we play after a reset will set the winner to the current winner from that first
   * round.
   */
  fun reset() {
    this.amountOfMeat = Random.nextInt(1000..2000)
    this.winningBid = 0
  }

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
