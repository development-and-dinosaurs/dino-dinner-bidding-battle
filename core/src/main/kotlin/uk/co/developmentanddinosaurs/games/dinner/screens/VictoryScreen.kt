package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy
import com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy
import com.badlogic.gdx.scenes.scene2d.ui.Image
import ktx.actors.centerPosition
import ktx.actors.repeatForever
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.assets.toInternalFile
import ktx.scene2d.image
import ktx.scene2d.scene2d
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex
import kotlin.random.Random

/**
 * The victory screen.
 *
 * The victory screen is the screen that shows who won the game.
 */
class VictoryScreen(
    private val stage: Stage,
    private val game: DinnerGame,
    private val mummyTrex: MummyTrex,
    private val assets: Assets,
) : KtxScreen {
  private val background = scene2d.image(assets.sprites["background"])
  private val music =
      Gdx.audio.newMusic("sounds/victory.mp3".toInternalFile()).apply {
        setOnCompletionListener { play() }
      }
  private val meats = listOf(spawnMeat(0), spawnMeat(1), spawnMeat(2), spawnMeat(3), spawnMeat(4))

  override fun show() {
    music.play()
    stage.addActor(background)
    val winner = getWinner()
    stage.addActor(winner)
    stage.addActor(getHat(winner))
    meats.forEach { stage.addActor(it) }
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
    meats.forEachIndexed { index, meat ->
      if (meat.y < -meat.height) {
        meat.x =
            Random.nextInt(
                    Gdx.graphics.width / meats.size * index,
                    (Gdx.graphics.width / meats.size * (index + 1)))
                .toFloat()
        meat.y =
            Gdx.graphics.height +
                Random.nextInt(meat.height.toInt(), meat.height.toInt() * meats.size).toFloat()
      }
    }
  }

  private fun getWinner(): Image {
    return scene2d.image(assets.sprites["carnivore_${mummyTrex.winner.colour()}"]) {
      centerPosition(Gdx.graphics.width.toFloat(), 0f)
      y = 100f
      addAction(moveBy(0f, 100f, 0.5f).then(moveBy(0f, -100f, 0.5f)).repeatForever())
    }
  }

  private fun getHat(winner: Image): Image {
    return scene2d.image(assets.sprites["hat_${mummyTrex.winner.hat()}"]) {
      it.x = winner.x
      it.y = winner.y + winner.height - 40
      addAction(moveBy(0f, 100f, 0.5f).then(moveBy(0f, -100f, 0.5f)).repeatForever())
    }
  }

  private fun spawnMeat(position: Int): Image {
    val image =
        scene2d.image(assets.sprites["meat"]).apply {
          setSize(128f, 128f)
          y =
              Gdx.graphics.height +
                  Random.nextInt(this.height.toInt(), this.height.toInt() * 5).toFloat()
          x =
              Random.nextInt(
                      Gdx.graphics.width / 5 * position, (Gdx.graphics.width / 5 * (position + 1)))
                  .toFloat()
          rotation = 120f
          addAction(
              Actions.parallel(
                      moveBy(0f, -150f, 0.5f), rotateBy(15f, .25f).then(rotateBy(-15f, .25f)))
                  .repeatForever(),
          )
        }
    return image
  }
}
