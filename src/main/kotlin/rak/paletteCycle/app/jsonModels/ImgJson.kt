package rak.pixellwp.cycling.jsonModels

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javafx.scene.paint.Color
import rak.paletteCycle.app.jsonModels.ColorJson
import rak.paletteCycle.app.display.Cycle

@JsonIgnoreProperties(ignoreUnknown = true)
data class ImgJson(val width: Int, val height: Int, val colors: List<ColorJson>, val cycles: List<Cycle>, val pixels: List<Int>){

    fun getParsedColors() : List<Color> {
        return colors.map { c ->  c.rgb}.toList()
    }


}
