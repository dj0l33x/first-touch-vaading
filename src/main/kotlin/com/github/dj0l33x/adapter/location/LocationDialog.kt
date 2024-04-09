package com.github.dj0l33x.adapter.location

import com.github.dj0l33x.port.location.CreateLocation
import com.github.dj0l33x.port.location.UpdateLocation
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.checkBox
import com.github.mvysny.karibudsl.v10.h3
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.github.mvysny.karibudsl.v10.textField
import com.github.mvysny.karibudsl.v23.footer
import com.github.mvysny.karibudsl.v23.header
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.dialog.Dialog
import com.vaadin.flow.data.binder.Binder

private val formWidth = "550px"

class LocationAddDialog : Dialog() {

    private val binder = Binder(CreateLocation::class.java)

    init {
        width = formWidth

        header {
            h3("New Location")
        }

        horizontalLayout {
            setWidthFull()
            textField {
                setWidthFull()
                isAutofocus = true
                placeholder = "Type path here..."
            }
            checkBox("Active") {

            }
        }

        footer {
            button("Cancel") {
                addThemeVariants(ButtonVariant.LUMO_ERROR)
                addClickShortcut(Key.ESCAPE)
                onLeftClick {
                    close()
                }
            }
            button("Add") {
                addThemeVariants(ButtonVariant.LUMO_PRIMARY)
                addClickShortcut(Key.ENTER)
                onLeftClick {
                    close()
                }
            }
        }
    }
}

class LocationUpdateDialog : Dialog() {
    private val binder = Binder(UpdateLocation::class.java)

    init {
        width = formWidth

        header {
            h3("Update Location")
        }

        horizontalLayout {
            setWidthFull()
            textField {
                setWidthFull()
                isAutofocus = true
                placeholder = "Type path here..."
            }
            checkBox("Active") {

            }
        }

        footer {
            button("Cancel") {
                addClickShortcut(Key.ESCAPE)
                onLeftClick {
                    close()
                }
            }
            button("Delete") {
                addThemeVariants(ButtonVariant.LUMO_ERROR)
                onLeftClick {
                    close()
                }
            }
            button("Update") {
                addThemeVariants(ButtonVariant.LUMO_PRIMARY)
                addClickShortcut(Key.ENTER)
                onLeftClick {
                    close()
                }
            }
        }
    }
}