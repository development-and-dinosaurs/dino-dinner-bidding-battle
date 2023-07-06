package uk.co.developmentanddinosaurs.games.dinner.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import ktx.assets.load

class Assets(private val assetManager: AssetManager) {

  val sprites = Sprites()

  fun finishLoading() {
    assetManager.finishLoading()
  }

  inner class Sprites {
    private val sprites =
        mapOf(
            "background" to assetManager.load<Texture>("sprites/background.jpg"),
            "meat" to assetManager.load<Texture>("sprites/meat.png"),
            "scroll" to assetManager.load<Texture>("sprites/scroll.png"),
            "carnivore_RED" to assetManager.load<Texture>("sprites/carnivores/carnivore_red.png"),
            "carnivore_ORANGE" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_orange.png"),
            "carnivore_YELLOW" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_yellow.png"),
            "carnivore_GREEN" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_green.png"),
            "carnivore_BLUE" to assetManager.load<Texture>("sprites/carnivores/carnivore_blue.png"),
            "carnivore_DARK_BLUE" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_dark_blue.png"),
            "carnivore_INDIGO" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_indigo.png"),
            "carnivore_VIOLET" to
                assetManager.load<Texture>("sprites/carnivores/carnivore_violet.png"),
            "hat_BANANA_SKIN" to assetManager.load<Texture>("sprites/hats/banana_skin.png"),
            "hat_CHEF_HAT" to assetManager.load<Texture>("sprites/hats/chef_hat.png"),
            "hat_COWBOY_HAT" to assetManager.load<Texture>("sprites/hats/cowboy_hat.png"),
            "hat_FOUR_LEAF_CLOVER" to
                assetManager.load<Texture>("sprites/hats/four_leaf_clover.png"),
            "hat_GRADUATION_CAP" to assetManager.load<Texture>("sprites/hats/graduation_cap.png"),
            "hat_PAPER_HAT" to assetManager.load<Texture>("sprites/hats/paper_hat.png"),
            "hat_PARTY_HAT" to assetManager.load<Texture>("sprites/hats/party_hat.png"),
            "hat_SANTA_HAT" to assetManager.load<Texture>("sprites/hats/santa_hat.png"),
            "hat_SOMBRERO" to assetManager.load<Texture>("sprites/hats/sombrero.png"),
            "hat_TOP_HAT" to assetManager.load<Texture>("sprites/hats/top_hat.png"),
        )

    operator fun get(name: String): Texture {
      return sprites[name]?.asset ?: throw NoSuchElementException("Could not find texture [$name]")
    }
  }
}
