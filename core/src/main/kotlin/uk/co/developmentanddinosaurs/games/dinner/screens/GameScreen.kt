package uk.co.developmentanddinosaurs.games.dinner.screens

import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreLoader
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d

/**
 * The game screen.
 *
 * The game screen is where the magic happens, and will perform all the required steps to play the game.
 */
class GameScreen(
    private val stage: Stage,
    private val game: DinnerGame,
    mummyTrex: MummyTrex,
    carnivoreLoader: CarnivoreLoader
) : KtxScreen {
    private val background = scene2d.image(Texture("sprites/background.jpg"))
    private val meat = scene2d.image(Texture("sprites/meat.png")).apply {
        x = (Gdx.graphics.width - this.width) / 2
        y = Gdx.graphics.height - this.height - 50
    }
    private val meatLabel = scene2d.label("${mummyTrex.bringHomeTheBacon()}\nkg").apply {
        setAlignment(1)
        x = meat.x + (meat.width / 2) - (this.width / 2)
        y = meat.y + (meat.height / 2) - (this.height / 2) - 50
    }
    private val dinoYs = listOf(25f, 75f)
    private val codeCarnivores = carnivoreLoader.loadCarnivores()
    private val carnivores = codeCarnivores.mapIndexed { index, carnivore ->
        val path = "sprites/carnivores/carnivore_${carnivore.colour().name.lowercase()}.png"
        scene2d.image(Texture(path)) {
            this.x = (index * (this.width + 10)) + 100
            this.y = dinoYs[index % 2]
        }
    }
    private val hats = codeCarnivores.mapIndexed { index, carnivore ->
        val path = "sprites/hats/${carnivore.hat().name.lowercase()}.png"
        val carnivoreImage = carnivores[index]
        scene2d.image(Texture(path)) {
            it.x = carnivoreImage.x
            it.y = carnivoreImage.y + carnivoreImage.height - 40
        }
    }

    override fun show() {
        stage.addActor(background)
        stage.addActor(meat)
        stage.addActor(meatLabel)
        carnivores.forEach { stage.addActor(it) }
        hats.forEach { stage.addActor(it) }
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
