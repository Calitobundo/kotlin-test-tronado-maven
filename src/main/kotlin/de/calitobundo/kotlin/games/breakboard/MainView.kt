package de.calitobundo.kotlin.games.breakboard

import javafx.geometry.Pos
import javafx.scene.input.KeyEvent
import tornadofx.*

class MainView : View("Hello TornadoFX") {

    companion object {
        const val CANVAS_WIDTH = 400.0
        const val CANVAS_HEIGHT = 300.0
    }

    lateinit var context: GameContext

    override val root = group {

        primaryStage.isAlwaysOnTop = true
        primaryStage.x = 2600.0
        primaryStage.y = 300.0

        canvas(CANVAS_WIDTH, CANVAS_HEIGHT) {
            context = GameContext(this, primaryStage)
        }

    }

}
