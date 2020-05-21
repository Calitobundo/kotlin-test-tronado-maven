package de.calitobundo.kotlin.games.breakboard

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class Player(private val s2: Vector): GameItem(s2) {

    init {
        width = 90.0
        height = 60.0
    }

    override fun update(delta: Double) {

        if(left && right || !left && !right)
            a.x = 0.0
        else if(left)
            a.x = -5000.0
        else if(right)
            a.x = 5000.0

        if(up && down || !up && !down)
            a.y = 0.0
        else if(up)
            a.y = -5000.0
        else if(down)
            a.y = 5000.0

        v.x += a.x * delta
        v.y += a.y * delta

        s.x += v.x * delta;
        s.y += v.y * delta;

        v.y *= 0.9
        v.x *= 0.9

        if(v.x > 500.0)
            v.x = 500.0

        if(v.x < -500.0)
            v.x = -500.0

        if(v.y > 500.0)
            v.y = 500.0

        if(v.y < -500.0)
            v.y = -500.0

        if(s.x < width/2){
            s.x = width/2
            v.x = 0.0
        }
        if(s.x > 400.0-width/2){
            s.x = 400.0-width/2
            v.x = 0.0
        }

        if(s.y < height/2){
            s.y = height/2
            //v.y *= -1
        }

        if(s.y > 300.0-height/2){
            s.y = 300.0-height/2
            //v.y *= -1
        }

    }

    override fun render(gc: GraphicsContext) {
        gc.fill = Color.WHITE
        gc.fillRect(s.x-width/2, s.y-height/2, width, height)
    }

    private var left = false;
    private var right = false;
    private var up = false;
    private var down = false;

    fun onLeft(pressed: Boolean){
        left = pressed
    }
    fun onRight(pressed: Boolean){
        right = pressed
    }
    fun onUp(pressed: Boolean){
        //up = pressed
    }
    fun onDown(pressed: Boolean){
        //down = pressed
    }
}