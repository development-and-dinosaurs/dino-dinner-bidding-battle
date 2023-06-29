package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn
import com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut
import com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy
import ktx.actors.centerPosition
import ktx.actors.onKeyDown
import ktx.actors.repeatForever
import ktx.actors.then
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.scene2d
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreColour
import uk.co.developmentanddinosaurs.games.dinner.CarnivoreHat
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

/**
 * The title screen.
 *
 * The title screen is the entrypoint into the game and the first screen that will be seen.
 */
class TitleScreen(private val stage: Stage, private val game: DinnerGame) : KtxScreen {
    private val background = scene2d.image(Texture("sprites/background.jpg"))
    private val title =
        scene2d.image(Texture("sprites/text/title.png")).apply {
            centerPosition(Gdx.graphics.width.toFloat(), 0f)
            y = Gdx.graphics.height - this.height - 25
        }
    private val meat =
        scene2d.image(Texture("sprites/meat.png")).apply {
            setSize(256f, 256f)
            centerPosition(Gdx.graphics.width.toFloat(), 0f)
            y = title.y - this.height
            addAction(rotateBy(16f, 0.5f).then(rotateBy(-16f, 0.5f)).repeatForever())
        }
    private val instructions =
        scene2d.image(Texture("sprites/text/press_start.png")).apply {
            centerPosition(Gdx.graphics.width.toFloat(), 0f)
            y = meat.y - this.height
            addAction(fadeOut(1f).then(fadeIn(1f)).repeatForever())
        }
    private val dinoYs = listOf(25f, 75f)
    private val carnivores =
        CarnivoreColour.values()
            .apply { shuffle() }
            .mapIndexed { index, colour ->
                val path = "sprites/carnivores/carnivore_${colour.name.lowercase()}.png"
                scene2d.image(Texture(path)) {
                    this.x = (index * (this.width + 10)) + 100
                    this.y = dinoYs[index % 2]
                }
            }
    private val allHats = CarnivoreHat.values().apply { shuffle() }.toMutableList()
    private val hats =
        carnivores.map { carnivore ->
            val path = "sprites/hats/${allHats.removeFirst().name.lowercase()}.png"
            scene2d.image(Texture(path)) {
                it.x = carnivore.x
                it.y = carnivore.y + carnivore.height - 40
            }
        }

    override fun show() {
        stage.addActor(background)
        stage.addActor(title)
        stage.addActor(meat)
        stage.addActor(instructions)
        carnivores.forEach { stage.addActor(it) }
        hats.forEach { stage.addActor(it) }
        stage.addActor(
            Actor().let { actor ->
                actor.onKeyDown { game.setScreen<GameScreen>() }
                stage.setKeyboardFocus(actor)
                actor
            },
        )
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
