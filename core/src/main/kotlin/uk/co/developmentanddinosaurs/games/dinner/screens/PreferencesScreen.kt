package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox
import com.badlogic.gdx.scenes.scene2d.ui.Container
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Scaling
import ktx.app.KtxScreen
import ktx.scene2d.checkBox
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.table
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame
import uk.co.developmentanddinosaurs.games.dinner.assets.Assets
import uk.co.developmentanddinosaurs.games.dinner.preferences.GamePreferences

class PreferencesScreen(
  private val stage: Stage,
  private val game: DinnerGame,
  private val preferences: GamePreferences,
  assets: Assets,
  skin: Skin,
) : KtxScreen {

  private val background = scene2d.image(assets.sprites["background"])

  private val musicVolume = Slider(0f, 1f, 0.1f, false, skin).apply {

    addListener {
      preferences.setMusicVolume(value)
      false
    }
    value = preferences.getMusicVolume()
  }

  private val container = Container(musicVolume).apply {
    isTransform = true
    setScale(2f)
  }
  private val table = scene2d.table {
    setFillParent(true)
    row().pad(20f)
    add(Label("Volume", skin)).align(Align.right)
    add(container).align(Align.left)
    row().pad(20f)
    add(Label("Vegetarian Mode", skin))
    add(CheckBox("", skin).apply { image.setScaling(Scaling.fill); imageCell.size(50f) }).align(Align.left)
  }

  override fun show() {
    stage.addActor(background)
    stage.addActor(table)
    Gdx.input.inputProcessor = stage
  }

  override fun render(delta: Float) {
    stage.act(delta)
    stage.draw()
  }
}