package uk.co.developmentanddinosaurs.games.dinner

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import java.io.File
import ktx.app.KtxGame
import ktx.assets.toInternalFile
import ktx.freetype.generateFont
import ktx.inject.Context
import ktx.inject.register
import ktx.scene2d.Scene2DSkin
import ktx.style.get
import ktx.style.label
import ktx.style.skin
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreLoader
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex
import uk.co.developmentanddinosaurs.games.dinner.screens.GameScreen
import uk.co.developmentanddinosaurs.games.dinner.screens.TitleScreen
import uk.co.developmentanddinosaurs.games.dinner.screens.VictoryScreen

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
      bind { Stage(inject(), inject()) }
      bindSingleton(this@DinnerGame)
      bindSingleton(MummyTrex())
      bindSingleton<Skin>(createSkin())
      Scene2DSkin.defaultSkin = context.inject<Skin>()
      bindSingleton<CarnivoreLoader>(CarnivoreLoader(File(Gdx.files.localStoragePath)))
      bindSingleton(TitleScreen(inject(), inject()))
      bindSingleton(GameScreen(inject(), inject(), inject(), inject()))
      bindSingleton(VictoryScreen(inject(), inject(), inject()))
    }
    Gdx.audio.newMusic("sounds/background.mp3".toInternalFile()).apply {
      setOnCompletionListener { play() }
    }
    // .play()
    addScreen(context.inject<TitleScreen>())
    addScreen(context.inject<GameScreen>())
    addScreen(context.inject<VictoryScreen>())
    setScreen<TitleScreen>()
  }

  private fun createSkin(): Skin = skin { skin ->
    add(
        "defaultFont",
        FreeTypeFontGenerator("fonts/Dinopia.otf".toInternalFile()).generateFont {
          size = 72
          borderWidth = 3f
          borderColor = Color.BLACK
        },
    )
    add(
        "scrollFont",
        FreeTypeFontGenerator("fonts/Dinopia.otf".toInternalFile()).generateFont {
          size = 36
          borderWidth = 3f
          borderColor = Color.BLACK
        },
    )
    label { font = skin["defaultFont"] }
    label("scroll") { font = skin["scrollFont"] }
  }

  override fun dispose() {
    context.remove<DinnerGame>()
    context.dispose()
  }
}
