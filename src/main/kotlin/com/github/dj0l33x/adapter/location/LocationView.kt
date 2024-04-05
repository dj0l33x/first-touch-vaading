package com.github.dj0l33x.adapter.location

import com.github.dj0l33x.port.location.LocationGet
import com.github.dj0l33x.port.location.LocationService
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.flexGrow
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.h3
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.notification.NotificationVariant
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
                h3("Locations")
                button("Add location") {
                    setPrimary()
                    onLeftClick {
                        Notification
                            .show("Success")
                            .addThemeVariants(NotificationVariant.LUMO_SUCCESS)

                    }
                }
                grid<LocationGet> {
                    flexGrow = 3.0
                    addColumn(LocationGet::id).setHeader("ID")
                    addColumn(LocationGet::data).setHeader("Data")
                    addColumn(LocationGet::createdAt).setHeader("Created")
                }.setItems(locationService.getLocations())
            }
        }
    }
}
