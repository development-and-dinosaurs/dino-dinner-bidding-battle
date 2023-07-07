package uk.co.developmentanddinosaurs.games.dinner.carnivore

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.delay
import com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import kotlin.properties.Delegates
import ktx.actors.then
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore

/**
 * Contains all the actors related to the carnivore.
 *
 * Orchestrates any actions that need to happen that related to a carnivore on the stage.
 */
class CarnivoreActor(
    val codeCarnivore: CraftyCodeCarnivore,
    private val image: Image,
    private val hat: Image,
    private val scroll: Image,
    private val label: Label,
) : Actor() {
  private var bid by Delegates.notNull<Int>()

  var wins = 0

  /**
   * Add carnivore actors to the stage.
   *
   * @param stage the stage to add actors to
   */
  fun addToStage(stage: Stage) {
    stage.addActor(image)
    stage.addActor(hat)
    stage.addActor(scroll)
    stage.addActor(label)
  }

  /** Have all carnivore actors act */
  override fun act(delta: Float) {
    image.act(delta)
    hat.act(delta)
    scroll.act(delta)
    label.act(delta)
    super.act(delta)
  }

  /**
   * Return the bid amount from the code carnivore
   *
   * @return the bid amount for the meat
   */
  fun bid(): Int {
    bid = codeCarnivore.bid()
    return bid
  }

  /**
   * Update the code carnivore with the amount of meat eaten.
   *
   * @param meatEaten the amount of meat eaten this round
   */
  fun update(meatEaten: Int) {
    codeCarnivore.update(meatEaten)
  }

  /**
   * Animate the bid actors to show the carnivore bid
   *
   * @return the action to animate the bid actors
   */
  fun showBid(): Action =
      Actions.run {
            label.setText(bid.toString())
            scroll.addAction(moveTo(image.x, image.y, 1f))
            label.addAction(moveTo(image.x, image.y, 1f))
          }
          .then(delay(1f))

  /**
   * Animate the bid actors to hide the carnivore bid
   *
   * @return the action to hide the bid actors
   */
  fun hideBid(): Action =
      Actions.run {
            scroll.addAction(moveTo(image.x, -150f, 1f))
            label.addAction(moveTo(image.x, -150f, 1f))
          }
          .then(delay(1f))

  fun wonGame() {
    wins += 1
    this.label.setText(wins.toString())
  }
}
