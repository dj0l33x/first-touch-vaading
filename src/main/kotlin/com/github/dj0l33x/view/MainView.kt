package com.github.dj0l33x.view

import com.github.dj0l33x.port.GreetService
import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.Key
import com.vaadin.flow.router.Route
import org.springframework.beans.factory.annotation.Autowired

@Route("default-view")
class MainView(@Autowired service: GreetService) : KComposite() {

    private val root = ui {
        // Use custom CSS classes to apply styling. This is defined in
        // styles.css.
        verticalLayout(classNames = "centered-content") {
            // Use TextField for standard text input
            val nameField = textField("Your name") {
                addClassName("bordered")
            }

            // Button click listeners can be defined as lambda expressions
            button("Say hello") {
                // Theme variants give you predefined extra styles for components.
                // Example: Primary button has a more prominent look.
                setPrimary()

                // You can specify keyboard shortcuts for buttons.
                // Example: Pressing enter in this view clicks the Button.
                addClickShortcut(Key.ENTER)

                onLeftClick {
                    this@verticalLayout.p(service.greet(nameField.value))
                }
            }
        }
    }
}
