package com.github.dj0l33x.adapter.location

import com.github.dj0l33x.port.location.LocationList
import com.github.dj0l33x.port.location.LocationService
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.flexGrow
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.h3
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.github.mvysny.karibudsl.v10.textField
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.notification.NotificationVariant
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = "locations")
@PageTitle("MALMA | Locations")
class LocationView(
    private val locationService: LocationService
) : KComposite() {

    init {
        ui {
            verticalLayout {
                h3("Search for a location")
                horizontalLayout {
                    textField {
                        setSizeFull()
                        placeholder = "Start typing..."
                        minLength = 3
                        maxLength = 255
                        valueChangeMode = ValueChangeMode.LAZY
                        isClearButtonVisible = true
                    }
                    button("Add location") {
                        setPrimary()
                        onLeftClick {
                            Notification
                                .show("Success")
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS)

                        }
                    }
                }
                grid<LocationList> {
                    flexGrow = 3.0
                    addColumn(LocationList::id).setHeader("ID")
                    addColumn(LocationList::data).setHeader("Data")
                    addColumn(LocationList::createdAt).setHeader("Created")
                }.setItems(locationService.getLocations())
            }
        }
    }
}
