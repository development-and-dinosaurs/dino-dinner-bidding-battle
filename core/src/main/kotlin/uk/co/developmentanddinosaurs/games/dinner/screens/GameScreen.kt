package uk.co.developmentanddinosaurs.games.dinner.screens

import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.app.KtxScreen

/**
 * The game screen.
 *
 * The game screen is where the magic happens, and will perform all the required steps to play the game.
 */
class GameScreen(private val stage: Stage, private val game: DinnerGame) : KtxScreen {
    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
