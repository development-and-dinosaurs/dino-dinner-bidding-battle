package uk.co.developmentanddinosaurs.games.dinner

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

/**
 * The Game instance for Dino Dinner Democracy.
 *
 * This is the entrypoint into the core game logic that is shared between distributions.
 */
class DinnerGame : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var img: Texture? = null
    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
    }

    override fun render() {
        ScreenUtils.clear(1f, 0f, 0f, 1f)
        batch!!.begin()
        batch!!.draw(img, 0f, 0f)
        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
    }
}
