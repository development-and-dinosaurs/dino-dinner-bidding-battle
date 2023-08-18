package uk.co.developmentanddinosaurs.games.dinner.preferences

import com.badlogic.gdx.Gdx

class GamePreferences {

  private val preferences = Gdx.app.getPreferences("Dinner")


  fun getMusicVolume(): Float {
    return preferences.getFloat("musicVolume", 0.5f)
  }

  fun setMusicVolume(volume: Float) {
    preferences.putFloat("musicVolume", volume)
    preferences.flush()
  }

  fun getHerbivoreMode(): Boolean {
    return preferences.getBoolean("herbivoreMode", false)
  }

  fun setHerbivoreMode(herbivoreMode: Boolean) {
    preferences.putBoolean("herbivoreMode", herbivoreMode)
    preferences.flush()
  }

}