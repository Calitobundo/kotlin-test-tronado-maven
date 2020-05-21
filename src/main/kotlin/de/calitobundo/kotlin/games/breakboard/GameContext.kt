package de.calitobundo.kotlin.games.breakboard

import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.stage.Stage
import java.util.*
import kotlin.collections.ArrayList


class GameContext(private val canvas: Canvas, private val stage: Stage) {

    companion object {
        private var debug = false;
    }

    private val loop: GameLoop = GameLoop(this);
    private val items: ArrayList<GameItem> = arrayListOf<GameItem>();

    private val player = Player(Vector(200.0, 255.0))

    init {
        stage.addEventHandler(KeyEvent.ANY, KeyHandler(player))
        stage.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseHandler())
        items.add(player)

        val rand = Random();
        for (i in 1..10) {
            val ball = Ball(Vector(200.0, 150.0))
            ball.v.y = 200.0 * (rand.nextDouble() - 0.5)
            ball.v.x = 200.0 * (rand.nextDouble() - 0.5)
            items.add(ball)
        }



//        val ball2 = Ball(Vector(200.0, 150.0))
//        ball2.v.y = 300.0
//        ball2.v.x = 50.0
//        items.add(ball2)


        loop.start()
        println("init")
    }

    fun onFrame(delta: Double){
        update(delta)
        render(canvas.graphicsContext2D)
    }

    /**
     *
     * GameItem Implementation
     *
     */
    private fun update(delta: Double) {
        items.forEach {
            it.updateItem(delta)
        }
        items.forEach {

             if(it != player){

                 val left1 = it.s.x - it.width/2
                 val right1 = it.s.x + it.width/2
                 val top1 = it.s.y - it.height/2
                 val bottom1 = it.s.y + it.height/2
                 val left2 = player.s.x - player.width/2
                 val right2 = player.s.x + player.width/2
                 val top2 = player.s.y - player.height/2
                 val bottom2 = player.s.y + player.height/2

                 val left = right1 > left2
                 val right = left1 < right2
                 val top = bottom1 > top2
                 val bottom = top1 < bottom2

                 val vertical = left && right
                 val horizontal = top && bottom

                 if(vertical && horizontal){
                     if(it.v.y < 0){
                         it.s.y = bottom1
                     }else{
                         it.s.y = top1
                     }
                     it.v.y *= -1
                 }

             }
        }
    }

    private fun render(gc: GraphicsContext) {
        gc.fill = Color.BLACK
        gc.fillRect(0.0, 0.0, MainView.CANVAS_WIDTH, MainView.CANVAS_HEIGHT)

        items.forEach {
            it.renderItem(gc)
        }
        if(debug)
            loop.render(gc)
    }



    /**
     *
     * Input Event Handlers
     *
     */
    class MouseHandler(): EventHandler<MouseEvent> {
        override fun handle(event: MouseEvent?) {
        }
    }

    class KeyHandler(private val player: Player): EventHandler<KeyEvent> {
        override fun handle(event: KeyEvent?) {
            if(event?.eventType == KeyEvent.KEY_PRESSED) {
                when (event?.code) {
                    KeyCode.LEFT -> player.onLeft(true)
                    KeyCode.RIGHT -> player.onRight(true)
                    KeyCode.UP -> player.onUp(true)
                    KeyCode.DOWN -> player.onDown(true)
                    KeyCode.D -> debug = !debug
                    else -> { // Note the block
                    }
                }
            }
            if(event?.eventType == KeyEvent.KEY_RELEASED) {
                when (event?.code) {
                    KeyCode.LEFT -> player.onLeft(false)
                    KeyCode.RIGHT -> player.onRight(false)
                    KeyCode.UP -> player.onUp(false)
                    KeyCode.DOWN -> player.onDown(false)
                    else -> { // Note the block
                    }
                }
            }
        }
    }

}





