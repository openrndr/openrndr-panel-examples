package org.openrndr.panel.examples.filedialog

import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.configuration
import org.openrndr.panel.ControlManager
import org.openrndr.panel.elements.button
import org.openrndr.panel.elements.dropdownButton
import org.openrndr.panel.elements.item
import org.openrndr.panel.elements.layout
import org.openrndr.panel.filedialogs.openFile
import org.openrndr.panel.style.defaultStyles

/**
 * In this example we show how to the use the file dialogs
 */
class Main : Program() {

    var bgColor = ColorRGBa.PINK
    override fun draw() {
        drawer.background(bgColor)
    }

    override fun setup() {
        val cm = ControlManager()
        cm.fontManager.register("default", "file:data/Roboto-Medium.ttf")
        cm.layouter.styleSheets.addAll(defaultStyles())

        // -- our body is just going to contain a single button
        cm.body = layout(cm) {
            dropdownButton {
                label = "background"
                item {
                    label = "pink"
                    events.picked.subscribe {
                        bgColor = ColorRGBa.PINK
                    }
                }
                item {
                    label = "black"
                    events.picked.subscribe {
                        bgColor = ColorRGBa.BLACK
                    }
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