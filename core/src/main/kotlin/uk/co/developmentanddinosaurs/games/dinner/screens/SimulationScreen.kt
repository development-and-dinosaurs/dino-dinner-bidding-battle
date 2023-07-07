package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import uk.co.developmentanddinosaurs.games.dinner.CraftyCodeCarnivore
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreActor
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex
import uk.co.developmentanddinosaurs.games.dinner.logic.RoundResult

/**
 * The simulation screen.
 *
 * The simulation screen is the screen that runs the simulation iterations and shows the results for
 * the ultimate winner.
 */
class SimulationScreen(
    private val stage: Stage,
    private val game: DinnerGame,
    private val mummyTrex: MummyTrex,
    private val codeCarnivores: List<CraftyCodeCarnivore>,
    assets: Assets
) : KtxScreen {
  private val background = scene2d.image(assets.sprites["background"])
  private val music = assets.music["simulation"]
  private val dinoYs = listOf(25f, 75f)
  private val carnivoreActors =
      codeCarnivores.mapIndexed { index, carnivore ->
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
              it.y = image.y
            }
        val label =
            scene2d.label("0", style = "scroll") {
              setAlignment(1)
              it.x = scroll.x
              it.y = scroll.y
              it.width = scroll.width
              it.height = scroll.height
            }
        CarnivoreActor(carnivore, image, hat, scroll, label)
      }

  private var gamesPlayed = 0

  override fun show() {
    music.play()
    stage.addActor(background)
    carnivoreActors.forEach { it.addToStage(stage) }
    Gdx.input.inputProcessor = stage
  }

  override fun hide() {
    music.stop()
  }

  override fun dispose() {
    music.dispose()
  }

  override fun render(delta: Float) {
    if (gamesPlayed < 100000) {
      for (i: Int in 1..20) {
        gamesPlayed += 1
        val winner = playGame()
        val winningActor = carnivoreActors[codeCarnivores.indexOf(winner)]
        winningActor.wonGame()
      }
    } else {
      mummyTrex.winner = carnivoreActors.maxBy { it.wins }.codeCarnivore
      game.setScreen<VictoryScreen>()
    }
    stage.act(delta)
    stage.draw()
  }

  private fun playGame(): CraftyCodeCarnivore {
    val carnivores = codeCarnivores.toMutableList()
    mummyTrex.reset()
    val meat = mummyTrex.bringHomeTheBacon()
    codeCarnivores.forEach { it.initialise(codeCarnivores.size, meat) }
    while (carnivores.isNotEmpty()) {
      val result = playRound(carnivores)
      mummyTrex.evaluate(result.winningCarnivore, result.winningBid)
      mummyTrex.updateMeat(result.winningBid)
      carnivores.remove(result.winningCarnivore)
      carnivores.forEach { it.update(result.winningBid) }
    }
    return mummyTrex.winner
  }

  private fun playRound(carnivores: List<CraftyCodeCarnivore>): RoundResult {
    val bids = carnivores.groupBy { it.bid() }
    val winningBid = bids.keys.min()
    return RoundResult(winningBid, bids[winningBid]!!.random())
  }
}
