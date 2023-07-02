package uk.co.developmentanddinosaurs.games.dinner.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import ktx.actors.centerPosition
import ktx.actors.onClick
import ktx.actors.onKeyDown
import ktx.app.KtxScreen
import ktx.scene2d.image
import ktx.scene2d.label
import ktx.scene2d.scene2d
import ktx.scene2d.textButton
import uk.co.developmentanddinosaurs.games.dinner.DinnerGame

/**
 * The instructions screen.
 *
 * The instructions screen is the screen that shows how to play the game.
 */
class InstructionsScreen(private val stage: Stage, private val game: DinnerGame) : KtxScreen {
  private val background = scene2d.image(Texture("sprites/background.jpg"))

  private val pageOneHeader = "Dino Dinner: Bidding Battle"
  private val pageOneInstructions =
      listOf(
          "Welcome to \"Dino Dinner: Bidding Battle\", a thrilling coding challenge",
          "where crafty carnivores running ancient algorithms compete in a",
          "progressive elimination auction to maximise their meat.",
          "",
          "Strategise your bids to secure the most meat while",
          "avoiding elimination, and prove your algorithm's prowess in",
          "this intense battle for carnivorous supremacy!",
          "",
          "The objective of the game is to maximize the amount of meat",
          "that your crafty carnivore receives throughout the rounds",
          "by strategically bidding against other participants.")

  private val pageTwoHeader = "Game Setup"
  private val pageTwoInstructions =
      listOf(
          "Up to ten participants can take part in the game simultaneously.",
          "Each participant must submit their algorithm's jar file before the game starts.",
          "",
          "You will be provided with a distributed library containing the interface ",
          "that your algorithm must work with. This library can be found on Maven Central.",
          "",
          "Once you've got the carnivore library set up, you can work on creating",
          "your crafty carnivore. You can get the full instructions on the README",
          "for the carnivore project, so check those out when you're ready.")

  private val pageThreeHeader = "Round Mechanics"
  private val pageThreeInstructions =
      listOf(
          "The game consists of a number of rounds equal to the number of players. ",
          "In each round, the crafty carnivores simultaneously place their bids for",
          "their share of the current amount of meat.",
          "",
          "At the start of the game you will receive information about how many players",
          "there are, and how much meat is up for grabs.",
          "",
          "After each round, you will be told how much meat was eaten.",
          "Carnivores that have been eliminated (by making a successful bid)",
          "will not receive further information.")

  private val pageFourHeader = "Bidding Rules"
  private val pageFourInstructions =
      listOf(
          "Crafty carnivores can bid any amount of meat they desire, up to",
          "the total amount of meat available in that round.",
          "",
          "Your goal is to bid an amount that ensures you end up with the most meat at",
          "the end of the game. You win a round by bidding for the least amount of ",
          "meat that round. After all rounds are complete, the carnivore ",
          "that secured the most meat is the winner.",
          "",
          "The crafty carnivore with the smallest bid in each round will",
          "be successful in their bid and removed from future rounds.",
          "The remaining participants move on to the next round.")

  private val pageFiveHeader = "Remembering Bids"
  private val pageFiveInstructions =
      listOf(
          "You are allowed to store historical bidding data within your program,",
          "either in memory or using the file system.",
          "",
          "This data can be used to inform your future bidding decisions.",
          "This won't necessarily help in the context of one game, but across thousands",
          "of iterations you should be able to make your carnivore craftier.")

  private val pageSixHeader = "Determining the Winner"
  private val pageSixInstructions =
      listOf(
          "The winner of an iteration of the game is the crafty carnivore that ",
          "received the most meat throughout the game, determined by having",
          "the highest winning bid across the rounds.",
          "",
          "The coding challenge can simulate several thousand iterations",
          "of the game to reveal the overall winner.",
          "",
          "The first iteration will be shown through the UI, but the ultimate",
          "winner will be determined based on the results of the simulation.")

  private val pageSevenHeader = "Supported Languages"
  private val pageSevenInstructions =
      listOf(
          "JVM languages are supported \"out of the box\", just ensure you",
          "build a jar file with your crafty carnivore class that implements",
          "the correct interface.",
          "",
          "You are free to use any other language, as long as you can wrap",
          "that in an executable jar. This is (currently) left",
          "as an exercise for the reader.")

  private val pageEightHeader = "Submitting Your Algorithm"
  private val pageEightInstructions =
      listOf(
          "Prepare your algorithm by implementing the interface",
          "provided in the distributed library.",
          "",
          "Package your algorithm as a jar file.",
          "",
          "Place your jar file in a \"carnivores\" directory in",
          "the same directory as the game jar.",
          "",
          "Run the game",
          "",
          "Good luck, and may the craftiest carnivore win!")
  private val pages =
      listOf(
          pageOneInstructions.mapIndexed { index, line -> label(line, index) },
          pageTwoInstructions.mapIndexed { index, line -> label(line, index) },
          pageThreeInstructions.mapIndexed { index, line -> label(line, index) },
          pageFourInstructions.mapIndexed { index, line -> label(line, index) },
          pageFiveInstructions.mapIndexed { index, line -> label(line, index) },
          pageSixInstructions.mapIndexed { index, line -> label(line, index) },
          pageSevenInstructions.mapIndexed { index, line -> label(line, index) },
          pageEightInstructions.mapIndexed { index, line -> label(line, index) },
      )
  private val headers =
      listOf(
          header(pageOneHeader),
          header(pageTwoHeader),
          header(pageThreeHeader),
          header(pageFourHeader),
          header(pageFiveHeader),
          header(pageSixHeader),
          header(pageSevenHeader),
          header(pageEightHeader),
      )
  private val nextButton =
      scene2d.textButton(">") {
        x = Gdx.graphics.width - this.width - 50
        y = 10f
        onClick {
          showPage(currentPage + 1)
          currentPage += 1
        }
      }
  private val previousButton =
      scene2d.textButton("<") {
        x = 50f
        y = 10f
        onClick {
          showPage(currentPage - 1)
          currentPage -= 1
        }
      }
  private val playButton =
      scene2d.textButton("Play") {
        centerPosition(Gdx.graphics.width.toFloat(), 0f)
        y = 10f
        onClick { game.setScreen<GameScreen>() }
      }

  private var currentPage = 0

  override fun show() {
    stage.addActor(background)
    headers.forEach { stage.addActor(it) }
    pages.flatten().forEach { stage.addActor(it) }
    stage.addActor(nextButton)
    stage.addActor(previousButton)
    stage.addActor(playButton)
    stage.addActor(
        Actor().let { actor ->
          actor.onKeyDown {
            if (it == Keys.LEFT && previousButton.isVisible) {
              showPage(currentPage - 1)
              currentPage -= 1
            }
            if (it == Keys.RIGHT && nextButton.isVisible) {
              showPage(currentPage + 1)
              currentPage += 1
            }
          }
          stage.setKeyboardFocus(actor)
          actor
        },
    )
    Gdx.input.inputProcessor = stage
    showPage(0)
  }

  override fun render(delta: Float) {
    stage.act(delta)
    stage.draw()
  }

  private fun showPage(page: Int) {
    pages.forEachIndexed { index, labels -> labels.forEach { it.isVisible = page == index } }
    headers.forEachIndexed { index, label -> label.isVisible = page == index }
    previousButton.isVisible = page != 0
    nextButton.isVisible = page != pages.size - 1
  }

  private fun label(line: String, index: Int): Label {
    return scene2d.label(line, style = "scroll").apply {
      centerPosition(Gdx.graphics.width.toFloat(), 0f)
      y = Gdx.graphics.height.toFloat() - ((index + 1) * 43f) - 150f
    }
  }

  private fun header(content: String): Label {
    return scene2d.label(content) {
      centerPosition(Gdx.graphics.width.toFloat(), 0f)
      y = Gdx.graphics.height - this.height - 50
    }
  }
}
