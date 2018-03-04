package org.openrndr.panel.examples.filedialog

import org.openrndr.Program
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.configuration
import org.openrndr.panel.ControlManager
import org.openrndr.panel.elements.button
import org.openrndr.panel.elements.layout
import org.openrndr.panel.filedialogs.openFile
import org.openrndr.panel.style.defaultStyles

/**
 * In this example we show how to the use the file dialogs
 */
class Main : Program() {
    override fun draw() {
        drawer.background(ColorRGBa.PINK)
    }

    override fun setup() {
        val cm = ControlManager()
        cm.fontManager.register("default", "file:data/Roboto-Medium.ttf")
        cm.layouter.styleSheets.addAll(defaultStyles())

        // -- our body is just going to contain a single button
        cm.body = layout(cm) {
            button {
                label = "open file"
                events.clicked.subscribe {
                    openFile {
                        println("yay I have $it")
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