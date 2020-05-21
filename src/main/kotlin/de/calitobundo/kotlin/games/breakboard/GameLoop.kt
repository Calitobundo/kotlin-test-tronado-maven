package de.calitobundo.kotlin.games.breakboard

import javafx.animation.AnimationTimer
import javafx.scene.canvas.GraphicsContext
import javafx.scene.text.Font

class GameLoop(private val context: GameContext) : AnimationTimer() {

    private val nanoPerSecond: Double = 1000000000.0
    private var before = 0L;

    private var fps = 0.0;
    private var timeFps = 0.0
    private var countFps = 0

    private var deltaTime = 0.0

    private var deltaMin = 10.0
    private var deltaMax = 0.0

    private var deltaMin2 = 0.0
    private var deltaMax2 = 0.0

    override fun handle(now: Long) {

        if(before == 0L)
            before = System.nanoTime();

        val delta: Double = now.toDouble() - before.toDouble()
        before = now

        deltaTime = delta/nanoPerSecond

        if(deltaTime > deltaMax)
            deltaMax = deltaTime

        if(deltaTime < deltaMin)
            deltaMin = deltaTime

        timeFps += deltaTime
        countFps++
        if(timeFps > 1.0){
            fps = countFps / timeFps
            countFps = 0
            timeFps = 0.0
            deltaMin2 = deltaMin
            deltaMax2 = deltaMax
            deltaMin = 10.0
            deltaMax = 0.0
        }
        context.onFrame(deltaTime)
    }

    fun render(gc: GraphicsContext){
        gc.font = Font("Verdena", 16.0)
        gc.fillText("fps: $fps", 5.0, 20.0)
        gc.fillText("delta: $deltaTime", 5.0, 40.0)
        gc.fillText("deltaMin: ${deltaMin2.toBigDecimal().toPlainString()}", 5.0, 60.0)
        gc.fillText("deltaMax: ${deltaMax2.toBigDecimal().toPlainString()}", 5.0, 80.0)

    }

}