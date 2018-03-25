package rak.paletteCycle.app.jsonModels

import javafx.scene.paint.Color

class ColorJson(rgbArray: IntArray){
    val rgb = parseColor(rgbArray)

    private fun parseColor(rgbArray: IntArray) : Color {
        val r: Int = rgbArray[0]
        val g: Int = rgbArray[1]
        val b: Int = rgbArray[2]
        return Color.rgb(r, g, b)
    }
}
