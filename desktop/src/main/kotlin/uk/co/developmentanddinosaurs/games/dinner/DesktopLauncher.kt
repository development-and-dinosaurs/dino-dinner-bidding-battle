/** Main entrypoint into the DesktopLauncher */
package uk.co.developmentanddinosaurs.games.dinner

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

/**
 * Entrypoint for the Desktop launcher. Simply runs a main function that creates the Dino Dinner
 * Democracy game and launches it using lwjgl3.
 */
fun main() {
  val config = Lwjgl3ApplicationConfiguration()
  config.setForegroundFPS(60)
  config.setTitle("Dino Dinner Democracy")
  config.setWindowedMode(1600, 900)
  config.setWindowIcon("sprites/meat.png")
  Lwjgl3Application(DinnerGame(), config)
}
