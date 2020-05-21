package de.calitobundo.kotlin.games.breakboard

import javafx.scene.canvas.GraphicsContext

abstract class GameItem(val s: Vector) {

    val a = Vector(0.0, 0.0);
    val v = Vector(0.0, 0.0);

    var width = 20.0
    var height = 20.0

    protected abstract fun update(delta: Double)
    protected abstract fun render(gc: GraphicsContext)


    fun renderItem(gc: GraphicsContext){
        render(gc)
    }

    fun updateItem(delta: Double){
        update(delta)
    }


    fun testCollision(player: GameItem){

        val box1 = CollisionBox(player)
        val box2 = CollisionBox(this)

        val top = box2.bottom > box1.top
        val bottom = box2.top < box1.bottom
        val left = box2.right > box1.left
        val right = box2.left < box1.right


    }

    class CollisionBox(private val item: GameItem){

        val left = item.s.x - item.width/2
        val right = item.s.x + item.width/2
        val top = item.s.y - item.height/2
        val bottom = item.s.y + item.height/2

    }



}