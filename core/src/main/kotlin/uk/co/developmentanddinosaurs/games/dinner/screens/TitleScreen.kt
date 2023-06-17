package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.actors.centerPosition
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.scene2d

/**
 * The title screen.
 *
 * The title screen is the entrypoint into the game and the first screen that will be seen.
 */
class TitleScreen(private val stage: Stage, private val game: DinnerGame) : KtxScreen {

    private val background = scene2d.image(Texture("sprites/background.jpg"))

    private val title = scene2d.image(Texture("sprites/text/title.png")).apply {
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = Gdx.graphics.height - this.height - 25f
    }

    override fun show() {
        stage.addActor(background)
        stage.addActor(title)
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
