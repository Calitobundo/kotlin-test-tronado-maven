package de.calitobundo.kotlin.games.breakboard

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class Ball(s2:Vector):GameItem(s2){

    private val size = 12.0

    init {
        width = 8.0
        height = 8.0
    }

    override fun update(delta: Double) {

        v.x += a.x * delta
        v.y += a.y * delta

        s.x += v.x * delta;
        s.y += v.y * delta;

        //v.y *= 0.9999
        //v.x *= 0.9999

        if(v.x > 500.0)
            v.x = 500.0

        if(v.x < -500.0)
            v.x = -500.0

        if(s.x < size/2){
            s.x = size/2
            v.x *= -1
        }
        if(s.x > 400.0-size/2){
            s.x = 400.0-size/2
            v.x *= -1
        }

        if(s.y < size/2){
            s.y = size/2
            v.y *= -1
        }

        if(s.y > 300.0-size/2){
            s.y = 300.0-size/2
            v.y *= -1
        }


    }

    override fun render(gc: GraphicsContext) {
        gc.fill = Color.WHITE
        gc.fillOval(s.x-size/2, s.y-size/2, size, size)
    }
}