package org.openrndr.panel.examples.sidebar.ui


import org.openrndr.color.ColorRGBa
import org.openrndr.panel.ControlManager
import org.openrndr.panel.collections.ObservableHashSet
import org.openrndr.panel.elements.*

class UIActions(
        val loadGlyphs: () -> Unit,
        val exportSVG: () -> Unit,
        val setImageSource: (String) -> Unit
)


fun ui(cm: ControlManager,
       actions: UIActions,

       masterMode: Boolean = true): Body {

    return layout(cm) {
        div("flex-container") {
            id = "container"
            div("harmonica") {

                div("harmonica-entry") {
                    p {
                        classes.add(ElementClass("harmonica-header"))
                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Image"
                    }

                    div("harmonica-content") {
                        dropdownButton {
                            label = "source"
                            item { "camera" }
                            item { "image" }
                            events.valueChanged.subscribe {
                                actions.setImageSource(it.value.label)
                            }
                        }

                        classes.add(ElementClass("harmonica-content-closed"))
                        toggle { id = "camera-mirror"; value = true; label = "mirror"  }
                        slider { id = "camera-contrast"; label = "contrast"; range = Range(0.0, 2.0); value = 1.0 }
                        slider { id = "camera-brightness"; label = "brightness"; range = Range(-1.0, 1.0); value = 0.0 }
                        slider { id = "camera-gamma"; label = "gamma"; range = Range(0.0, 4.0); value = 1.0 }
                        toggle { id = "camera-monochrome"; label = "monochrome"; value = true }
                        slider { id = "camera-motion-blur"; label = "motion blur"; range = Range(0.0, 1.0); value = 0.0; if (!masterMode) classes.add(ElementClass("hidden")) }
                        slider { id = "camera-noise-intensity"; label = "noise"; range = Range(0.0, 1.0); value = 0.0; if (!masterMode) classes.add(ElementClass("hidden")) }
                    }
                }

                div("harmonica-entry") {
                    p {
                        classes.add(ElementClass("harmonica-header"))
                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Trackball"
                    }

                    div("harmonica-content") {
                        classes.add(ElementClass("harmonica-content-closed"))
                        slider { id = "zoom-min-soft"; label = "zoom soft min."; range = Range(4.0, 512.0); value = 16.0 }
                        slider { id = "zoom-min-hard"; label = "zoom hard min."; range = Range(4.0, 512.0); value = 16.0 }
                        slider { id = "zoom-max-soft"; label = "zoom soft max."; range = Range(8.0, 512.0); value = 512.0 }
                        slider { id = "zoom-max-hard"; label = "zoom hard max."; range = Range(8.0, 512.0); value = 512.0 }
                        slider { id = "zoom-rest"; label = "zoom rest"; range = Range(8.0, 512.0); value = 16.0 }
                        slider { id = "zoom-sensitivity"; label = "zoom sensitivity"; range = Range(0.0, 2.0); value = 1.0 }
                    }
                }

                div("harmonica-entry") {
                    if (!masterMode) {
                        classes.add(ElementClass("hidden"))
                    }
                    p {
                        classes.add(ElementClass("harmonica-header"))
                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Glyphs"
                    }
                    div("harmonica-content") {
                        classes.add(ElementClass("harmonica-content-closed"))
                        slider { id = "glyph-low"; label = "low"; range = Range(0.0, 1.0); value = 0.08 }
                        slider { id = "glyph-high"; label = "high"; range = Range(0.0, 1.0); value = 1.0 }
                        slider { id = "glyph-scale"; label = "scale"; range = Range(0.0, 4.0); value = 1.0 }
                        toggle { id = "glyph-random-subsets"; value = true; label = "random glyph sets" }
                        button {
                            id = "glyph-load-glyphs"; label = "load glyphs";
                            events.clicked.subscribe { actions.loadGlyphs() }
                        }
                    }
                }

                div("harmonica-entry") {
                    if (!masterMode) {
                        classes.add(ElementClass("hidden"))
                    }

                    p {
                        classes.add(ElementClass("harmonica-header"))

                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Crop"
                    }
                    div("harmonica-content") {

                        classes.add(ElementClass("harmonica-content-closed"))
                        slider { id = "crop-horizontal"; label = "horizontal"; range = Range(0.0, 1.0); value = 0.5 }
                        slider { id = "crop-vertical"; label = "vertical"; range = Range(0.0, 1.0); value = 0.5 }
                    }
                }


                div("harmonica-entry") {
                    if (!masterMode) {
                        classes.add(ElementClass("hidden"))
                    }

                    p {
                        classes.add(ElementClass("harmonica-header"))

                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Zoom"
                    }
                    div("harmonica-content") {
                        classes.add(ElementClass("harmonica-content-closed"))
                        slider { id = "zoom-level"; label = "zoom level"; range = Range(8.0, 512.0); value = 16.0 }
                    }
                }

                div("harmonica-entry") {
                    if (!masterMode) {
                        classes.add(ElementClass("hidden"))
                    }

                    p {
                        classes.add(ElementClass("harmonica-header"))

                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Colors"
                    }
                    div("harmonica-content") {
                        classes.add(ElementClass("harmonica-content-closed"))
                        slider { id = "color-opacity"; label = "opacity"; range = Range(0.0, 1.0); value = 1.0 }
                        slider { id = "color-separation"; label = "separation"; range = Range(0.0, 16.0); value = 0.0 }
//                        slider { id = "color-grid-strength"; label = "grid strength"; range = Range(0.0, 1.0); value = 0.0 }
//                        slider { id = "color-grid-base"; label = "grid base"; range = Range(0.0, 1.0); value = 0.0 }

                        val magenta = ColorRGBa(1.0, 5.0 / 255.0, 226 / 255.0)
                        val cyan = ColorRGBa(0.0, 176.0 / 255.0, 1.0)
                        val yellow = ColorRGBa(250.0 / 255.0, 253.0 / 255.0, 38.0 / 255.0)
                        colorpicker { id = "color-background"; label = "background"; color = ColorRGBa.WHITE }
                        colorpicker { id = "color-cyan"; label = "cyan"; color = cyan }
                        colorpicker { id = "color-magenta"; label = "magenta"; color = magenta }
                        colorpicker { id = "color-yellow"; label = "yellow"; color = yellow }
                    }
                }
                div("harmonica-entry") {
                    if (!masterMode) {
                        classes.add(ElementClass("hidden"))
                    }
                    p {
                        classes.add(ElementClass("harmonica-header"))
                        mouse.clicked.subscribe { next()?.classes?.toggle(ElementClass("harmonica-content-closed")) }
                        "Export"
                    }
                    div("harmonica-content") {
                        classes.add(ElementClass("harmonica-content-closed"))
                        button {
                            id = "glyph-export-svg"; label = "export";
                            events.clicked.subscribe { actions.exportSVG() }
                        }
                    }
                }
            }
        }
    }
}

// this should be part of Panel really.
private fun <E> ObservableHashSet<E>.toggle(elementClass: E) {
    if (elementClass in this) {
        remove(elementClass)
    } else {
        add(elementClass)
    }
}
