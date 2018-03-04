package org.openrndr.panel.examples.flexbox001

import org.openrndr.Configuration
import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.panel.controlManager
import org.openrndr.panel.elements.*
import org.openrndr.panel.style.*

/**
 * In this example we show how to create a vertical control panel with two sliders
 */
class Main : Program() {
    var intensity = 1.0
    var hue = 0.0
    override fun draw() {
        drawer.background(ColorRGBa.RED.toHSVa().shiftHue(360.0 * hue).toRGBa().shade(intensity))
    }

    override fun setup() {
        val cm = controlManager {

            // -- create some styles here
            styleSheet {
                selector = selector { `class`("container") }
                width = 150.px
                background = Color.RGBa(ColorRGBa.GRAY)
                height = 100.percent
                display = Display.FLEX
                flexDirection = FlexDirection.Column

                withChild {
                    selector = selector { type("slider") }
                    height = 40.px
                }
            }

            // -- create the layout here
            layout {
                div("container") {
                    slider {
                        label = "intensity"
                        value = intensity
                        range = Range(0.0, 1.0)
                        events.valueChanged.subscribe {
                            intensity = it.newValue
                        }
                    }
                    slider {
                        label = "hue"
                        value = hue
                        range = Range(0.0, 1.0)
                        events.valueChanged.subscribe {
                            hue = it.newValue
                        }
                    }
                }
            }
        }
        extend(cm)
    }
}

fun main(args: Array<String>) {
    application(Main(), Configuration())
}