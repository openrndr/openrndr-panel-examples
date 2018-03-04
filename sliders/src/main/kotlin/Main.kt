package org.openrndr.panel.examples.sliders

import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.configuration
import org.openrndr.panel.ControlManager
import org.openrndr.panel.elements.*
import org.openrndr.panel.filedialogs.openFile
import org.openrndr.panel.style.defaultStyles

/**
 * In this example we show how to the use the sliders
 */
class Main : Program() {

    var intensity = 1.0
    override fun draw() {
        drawer.background(ColorRGBa.PINK.shade(intensity))
    }

    override fun setup() {
        val cm = ControlManager()
        cm.fontManager.register("default", "file:data/Roboto-Medium.ttf")
        cm.layouter.styleSheets.addAll(defaultStyles())

        cm.body = layout(cm) {
            slider {
                label = "intensity"
                value = intensity
                range = Range(0.0, 1.0)
                events.valueChanged.subscribe {
                    intensity = it.newValue
                }
            }
        }
        extend(cm)
    }
}

fun main(args: Array<String>) {
    application(Main(), configuration {
    })
}