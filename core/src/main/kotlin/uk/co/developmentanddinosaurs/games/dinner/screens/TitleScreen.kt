package uk.co.developmentanddinosaurs.games.dinner.screens

import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.scene2d

/**
 * The title screen.
 *
 * The title screen is the entrypoint into the game and the first screen that will be seen.
 */
class TitleScreen(private val stage: Stage, private val game: DinnerGame) : KtxScreen {
    override fun show() {
        stage.addActor(scene2d.image(Texture("sprites/background.jpg")))
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
