package com.github.dj0l33x.adapter.location

import com.github.dj0l33x.port.location.LocationDto
import com.github.dj0l33x.port.location.LocationService
import com.github.mvysny.karibudsl.v10.KComposite
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.flexGrow
import com.github.mvysny.karibudsl.v10.grid
import com.github.mvysny.karibudsl.v10.h3
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.textField
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route(value = "locations")
@PageTitle("MALMA | Locations")
class LocationView(
    private val locationService: LocationService
) : KComposite() {

    private val root = ui {
        verticalLayout {
            h3("Search for a location")
            horizontalLayout {
                textField {
                    placeholder = "Start typing..."
                    minLength = 3
                    maxLength = 255
                    valueChangeMode = ValueChangeMode.LAZY
                    isClearButtonVisible = true
                }
                button("Add new location") { }
            }

            grid<LocationDto> {
                flexGrow = 2.0
                addColumn(LocationDto::id).setHeader("ID")
                addColumn(LocationDto::data).setHeader("Data")
                addColumn(LocationDto::createdAt).setHeader("Created")
            }.setItems(locationService.getLocations())


        }
    }

}