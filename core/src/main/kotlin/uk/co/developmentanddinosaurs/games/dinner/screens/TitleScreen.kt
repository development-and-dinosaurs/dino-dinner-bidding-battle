package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy
import kotlin.system.exitProcess
import ktx.actors.centerPosition
import ktx.actors.onClick
import ktx.actors.repeatForever
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.textButton
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreColour
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreHat
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets

/**
 * The title screen.
 *
 * The title screen is the entrypoint into the game and the first screen that will be seen.
 */
class TitleScreen(
    private val stage: Stage,
    private val game: DinnerGame,
    assets: Assets,
) : KtxScreen {
  private val background = scene2d.image(assets.sprites["background"])
  private val music = assets.music["title"]
  private val title =
      scene2d.label("Dino Dinner: Bidding Battle", style = "title") {
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = Gdx.graphics.height - this.height - 10
      }
  private val meat =
      scene2d.image(assets.sprites["meat"]).apply {
        setSize(256f, 256f)
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = title.y - this.height - 20
        addAction(rotateBy(16f, 0.5f).then(rotateBy(-16f, 0.5f)).repeatForever())
      }
  private val playButton =
      scene2d.textButton("Play") {
        x = 50f
        y = 250f
        onClick {
          println("Clicked play")
          game.setScreen<GameScreen>()
        }
      }
  private val simulationButton =
      scene2d.textButton("Simulation") {
        x = 50f
        y = 175f
        onClick { game.setScreen<SimulationScreen>() }
      }
  private val instructionsButton =
      scene2d.textButton("Instructions") {
        x = 50f
        y = 100f
        onClick { game.setScreen<InstructionsScreen>() }
      }
  private val quitButton =
      scene2d.textButton("Quit") {
        x = 50f
        y = 25f
        onClick { exitProcess(0) }
      }
  private val dinoYs = listOf(25f, 75f)
  private val carnivores =
      CarnivoreColour.values()
          .apply { shuffle() }
          .take(6)
          .mapIndexed { index, colour ->
            scene2d.image(assets.sprites["carnivore_$colour"]) {
              this.x = (index * (this.width + 10)) + 500
              this.y = dinoYs[index % 2]
            }
          }
  private val allHats = CarnivoreHat.values().apply { shuffle() }.toMutableList()
  private val hats =
      carnivores.map { carnivore ->
        scene2d.image(assets.sprites["hat_${allHats.removeFirst()}"]) {
          it.x = carnivore.x
          it.y = carnivore.y + carnivore.height - 40
        }
      }

  override fun show() {
    music.play()
    stage.addActor(background)
    stage.addActor(title)
    stage.addActor(meat)
    stage.addActor(playButton)
    stage.addActor(simulationButton)
    stage.addActor(instructionsButton)
    stage.addActor(quitButton)
    carnivores.forEach { stage.addActor(it) }
    hats.forEach { stage.addActor(it) }
    Gdx.input.inputProcessor = stage
  }

  override fun hide() {
    music.stop()
  }

  override fun render(delta: Float) {
    stage.act(delta)
    stage.draw()
  }

  override fun dispose() {
    music.dispose()
  }
}
