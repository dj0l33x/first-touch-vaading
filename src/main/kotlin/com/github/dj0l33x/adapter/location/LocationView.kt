package com.github.dj0l33x.adapter.location

import com.github.dj0l33x.port.location.GetLocation
import com.github.dj0l33x.port.location.LocationService
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.h3
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.github.mvysny.kaributools.setPrimary
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
                val dialog = LocationAddDialog()
                h3("Locations")
                button("Add location") {
                    setPrimary()
                    onLeftClick {
                        dialog.open()
                    }
                }
                grid<GetLocation> {
                    addColumn(GetLocation::id).setHeader("ID")
                    addColumn(GetLocation::path).setHeader("Data")
                    addColumn(GetLocation::isActive).setHeader("Active")
                    addColumn(GetLocation::createdAt).setHeader("Created")
                    addColumn(GetLocation::updatedAt).setHeader("Updated")
                    addColumn(GetLocation::deletedAt).setHeader("Deleted")
                }.setItems(locationService.loadAllLocations())
            }
        }
    }
}
