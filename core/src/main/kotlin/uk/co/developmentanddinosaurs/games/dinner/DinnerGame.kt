package uk.co.developmentanddinosaurs.games.dinner

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import java.io.File
import ktx.app.KtxGame
import ktx.inject.Context
import ktx.inject.register
import ktx.scene2d.Scene2DSkin
import ktx.style.get
import ktx.style.label
import ktx.style.skin
import ktx.style.textButton
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets
import uk.co.developmentanddinosaurs.games.dinner.carnivore.CarnivoreLoader
import uk.co.developmentanddinosaurs.games.dinner.logic.MummyTrex
import uk.co.developmentanddinosaurs.games.dinner.screens.GameScreen
import uk.co.developmentanddinosaurs.games.dinner.screens.InstructionsScreen
import uk.co.developmentanddinosaurs.games.dinner.screens.TitleScreen
import uk.co.developmentanddinosaurs.games.dinner.screens.VictoryScreen

/**
 * The Game instance for Dino Dinner: Bidding Battle.
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
      bindSingleton(Assets(AssetManager()))
      inject<Assets>().finishLoading()
      bindSingleton<Skin>(createSkin(inject()))
      Scene2DSkin.defaultSkin = context.inject<Skin>()
      bindSingleton<CarnivoreLoader>(CarnivoreLoader(File(Gdx.files.localStoragePath)))
      bindSingleton(TitleScreen(inject(), inject(), inject()))
      bindSingleton(InstructionsScreen(inject(), inject(), inject()))
      bindSingleton(GameScreen(inject(), inject(), inject(), inject(), inject()))
      bindSingleton(VictoryScreen(inject(), inject(), inject(), inject()))
    }
    addScreen(context.inject<TitleScreen>())
    addScreen(context.inject<InstructionsScreen>())
    addScreen(context.inject<GameScreen>())
    addScreen(context.inject<VictoryScreen>())
    setScreen<TitleScreen>()
  }

  private fun createSkin(assets: Assets): Skin = skin { skin ->
    add("defaultFont", assets.fonts["defaultFont"])
    add("titleFont", assets.fonts["titleFont"])
    add("scrollFont", assets.fonts["scrollFont"])
    label { font = skin["defaultFont"] }
    label("title") { font = skin["titleFont"] }
    label("scroll") { font = skin["scrollFont"] }
    textButton {
      font = skin["defaultFont"]
      overFontColor = Color.ORANGE
      downFontColor = Color.CORAL
    }
  }

  override fun dispose() {
    context.remove<DinnerGame>()
    context.dispose()
  }
}
