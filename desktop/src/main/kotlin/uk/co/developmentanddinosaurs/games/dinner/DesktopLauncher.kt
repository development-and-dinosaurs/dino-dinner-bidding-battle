package uk.co.developmentanddinosaurs.games.dinner

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

fun main() {
    val config = Lwjgl3ApplicationConfiguration()
    config.setForegroundFPS(60)
    config.setTitle("Dino Dinner Democracy")
    Lwjgl3Application(DinnerGame(), config)
}
