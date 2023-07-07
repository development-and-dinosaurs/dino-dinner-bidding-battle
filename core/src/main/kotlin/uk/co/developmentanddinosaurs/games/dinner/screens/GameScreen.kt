package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import ktx.actors.onKeyDown
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreActor
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
    private val codeCarnivores: List<CraftyCodeCarnivore>,
    assets: Assets,
) : KtxScreen {
  private val background = scene2d.image(assets.sprites["background"])
  private val music = assets.music["game"]
  private val meat =
      scene2d.image(assets.sprites["meat"]).apply {
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
  private val carnivoreActors =
      codeCarnivores
          .mapIndexed { index, carnivore ->
            val image =
                scene2d.image(assets.sprites["carnivore_${carnivore.colour()}"]) {
                  this.x = (index * (this.width + 10)) + 100
                  this.y = dinoYs[index % 2]
                }
            val hat =
                scene2d.image(assets.sprites["hat_${carnivore.hat()}"]) {
                  it.x = image.x
                  it.y = image.y + image.height - 40
                }
            val scroll =
                scene2d.image(assets.sprites["scroll"]) {
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
            CarnivoreActor(carnivore, image, hat, scroll, label)
          }
          .toMutableList()
  private var canPlayRound = true

  override fun show() {
    music.play()
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

  override fun hide() {
    music.stop()
  }

  override fun dispose() {
    music.dispose()
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
