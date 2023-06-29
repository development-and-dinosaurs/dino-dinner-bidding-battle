package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import ktx.actors.onKeyDown
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreActor
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreLoader
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex

/**
 * The game screen.
 *
 * The game screen is where the magic happens, and will perform all the required steps to play the
 * game.
 */
class GameScreen(
    private val stage: Stage,
    private val game: DinnerGame,
    private val mummyTrex: MummyTrex,
    carnivoreLoader: CarnivoreLoader,
) : KtxScreen {
  private val background = scene2d.image(Texture("sprites/background.jpg"))
  private val meat =
      scene2d.image(Texture("sprites/meat.png")).apply {
        x = (Gdx.graphics.width - this.width) / 2
        y = Gdx.graphics.height - this.height - 50
      }
  private val meatLabel =
      scene2d.label("${mummyTrex.bringHomeTheBacon()}\nkg").apply {
        setAlignment(1)
        x = meat.x + (meat.width / 2) - (this.width / 2)
        y = meat.y + (meat.height / 2) - (this.height / 2) - 50
      }
  private val dinoYs = listOf(25f, 75f)
  private val codeCarnivores = carnivoreLoader.loadCarnivores()
  private val carnivoreActors =
      codeCarnivores
          .mapIndexed { index, carnivore ->
            val image =
                scene2d.image(
                    Texture(
                        "sprites/carnivores/carnivore_${carnivore.colour().name.lowercase()}.png")) {
                      this.x = (index * (this.width + 10)) + 100
                      this.y = dinoYs[index % 2]
                    }
            val hat =
                scene2d.image(Texture("sprites/hats/${carnivore.hat().name.lowercase()}.png")) {
                  it.x = image.x
                  it.y = image.y + image.height - 40
                }
            val scroll =
                scene2d.image(Texture("sprites/scroll.png")) {
                  it.x = image.x
                  it.y = -150f
                }
            val label =
                scene2d.label("1234", style = "scroll") {
                  setAlignment(1)
                  it.x = scroll.x
                  it.y = -150f
                  it.width = scroll.width
                  it.height = scroll.height
                }
            val miniMeat =
                scene2d.image(Texture("sprites/meat.png")) {
                  setSize(128f, 128f)
                  it.x = image.x
                  it.y = Gdx.graphics.height + it.height
                }
            CarnivoreActor(carnivore, image, hat, scroll, label, miniMeat)
          }
          .toMutableList()
  private var canPlayRound = true

  override fun show() {
    stage.addActor(background)
    stage.addActor(meat)
    stage.addActor(meatLabel)
    carnivoreActors.forEach { it.addToStage(stage) }
    stage.addActor(
        Actor().let { actor ->
          actor.onKeyDown { playRound() }
          stage.setKeyboardFocus(actor)
          actor
        },
    )
    codeCarnivores.forEach { it.initialise(codeCarnivores.size, mummyTrex.bringHomeTheBacon()) }
    Gdx.input.inputProcessor = stage
  }

  override fun render(delta: Float) {
    stage.act(delta)
    stage.draw()
  }

  private fun playRound() {
    if (carnivoreActors.isEmpty()) {
      game.setScreen<VictoryScreen>()
      return
    }
    val bids = carnivoreActors.map { it.bid() }
    val showActions = carnivoreActors.map { it.showBid() }
    val winningBid = bids.min()
    val winner = carnivoreActors.removeAt(bids.indexOf(winningBid))
    val hideActions = carnivoreActors.map { it.hideBid() }
    stage.addAction(
        Actions.parallel(*showActions.toTypedArray())
            .then(Actions.delay(3f))
            .then(Actions.parallel(*hideActions.toTypedArray()))
            .then(Actions.run { meatLabel.setText("${mummyTrex.bringHomeTheBacon()}\nkg") })
            .then(Actions.run { playRound() }),
    )
    carnivoreActors.forEach { it.update(winningBid) }
    mummyTrex.updateMeat(winningBid)
    mummyTrex.evaluate(winner.codeCarnivore, winningBid)
  }
}
