package uk.co.developmentanddinosaurs.games.dinner

import uk.co.developmentanddinosaurs.games.dinner.screens.TitleScreen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxGame
import ktx.inject.Context
import ktx.inject.register

/**
 * The Game instance for Dino Dinner Democracy.
 *
 * This is the entrypoint into the core game logic that is shared between distributions.
 */
class DinnerGame : KtxGame<Screen>() {
    private val context = Context()

    override fun create() {
        context.register {
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton<Viewport>(ScreenViewport())
            bindSingleton(Stage(inject(), inject()))
            bindSingleton(this@DinnerGame)
            bindSingleton(TitleScreen(inject(), inject()))
        }
        addScreen(context.inject<TitleScreen>())
        setScreen<TitleScreen>()
    }

    override fun dispose() {
        context.remove<DinnerGame>()
        context.dispose()
    }
}
