package uk.co.developmentanddinosaurs.games.dinner.screens

import uk.co.developmentanddinosaurs.games.dinner.CarnivoreColour
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
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
        y = Gdx.graphics.height - this.height - 25
    }
    private val meat = scene2d.image(Texture("sprites/meat.png")).apply {
        setSize(256f, 256f)
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = title.y - this.height
        addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.rotateBy(16f, 0.5f),
                    Actions.rotateBy(-16f, 0.5f)
                )
            )
        )
    }
    private val instructions = scene2d.image(Texture("sprites/text/press_start.png")).apply {
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = meat.y - this.height
        addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.fadeOut(1f),
                    Actions.fadeIn(1f)
                )
            )
        )
    }
    private val dinoYs = listOf(25f, 75f)
    private val carnivores = CarnivoreColour.values().apply { shuffle() }.mapIndexed { index, colour ->
        val spriteColour = colour.name.lowercase()
        val path = "sprites/carnivores/carnivore_$spriteColour.png"
        scene2d.image(Texture(path)) {
            this.x = (index * (this.width + 10)) + 100
            this.y = dinoYs[index % 2]
        }
    }

    override fun show() {
        stage.addActor(background)
        stage.addActor(title)
        stage.addActor(meat)
        stage.addActor(instructions)
        carnivores.forEach { stage.addActor(it) }
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
